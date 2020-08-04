package com.thinkcms.freemark.observers;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thinkcms.core.config.ThinkCmsConfig;
import com.thinkcms.core.constants.Constants;
import com.thinkcms.core.handler.CustomException;
import com.thinkcms.core.utils.ApiResult;
import com.thinkcms.core.utils.Checker;
import com.thinkcms.core.utils.SecurityConstants;
import com.thinkcms.freemark.components.TemplateComponent;
import com.thinkcms.freemark.corelibs.notify.AbstractNotify;
import com.thinkcms.freemark.corelibs.observer.AdapterObserver;
import com.thinkcms.freemark.corelibs.observer.ObserverAction;
import com.thinkcms.freemark.corelibs.observer.ObserverData;
import com.thinkcms.freemark.corelibs.observer.data.ContentCreateObserverData;
import com.thinkcms.freemark.tools.ContentHelp;
import com.thinkcms.service.api.category.CmsCategoryAttributeService;
import com.thinkcms.service.api.content.ContentService;
import com.thinkcms.service.dto.content.ContentDto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Data
@Component
@Slf4j
public class ContentAllObserver extends AdapterObserver {

    @Autowired
    TemplateComponent templateComponent;

    @Autowired
    ContentService contentService;

    @Autowired
    ThinkCmsConfig thinkCmsConfig;

    @Autowired
    CmsCategoryAttributeService cmsCategoryAttributeService;

    private AbstractNotify notifyRes;

    private int step = 0;


    @Override
    public void update(ObserverData data, AbstractNotify notifyRes) {
        // 组装数据 创建静态页
        if (Checker.BeNotNull(data)) {
            if (ObserverAction.valueOf(data.getObserverAction().getCode()).equals(ObserverAction.CONTENT_CREATE)) {
                this.notifyRes = notifyRes;
                try {
                    ApiResult apiResult = genStaticContentFile(data);
                    if (!apiResult.ckSuccess()) {
                        throw new CustomException(apiResult);
                    }
                } finally {
                    this.notifyRes = null;
                    this.step = 0;
                }
            }
        }
    }

    private ApiResult genStaticContentFile(ObserverData data) {
        if (data instanceof ContentCreateObserverData) {
            pageContent(data.getCreateId(), 1);
        }
        return ApiResult.result();
    }


    private void notifyFront(ApiResult apiResult, IPage<ContentDto> page) {
        if (apiResult.ckSuccess()) {
            step += 1;
            BigDecimal current =new BigDecimal(page.getCurrent());
            BigDecimal totalPage =new BigDecimal(page.getPages());
            BigDecimal progress=current.divide(totalPage,2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
            apiResult.put(Constants.progress,progress.intValue());
            if (Checker.BeNotNull(this.notifyRes)) {
                this.notifyRes.notifyOnceSuccess(apiResult);
            }

        }
    }

    private ApiResult pageContent(String createId, Integer pageNo) {
        ApiResult apiResult = ApiResult.result();
        Integer pageSize = 10;
        IPage<ContentDto> pages = new Page<>(pageNo, pageSize);
        IPage<ContentDto> result = contentService.pageAllContentForGen(pages, Arrays.asList(new String[]{"1"}));
        if (result.getCurrent() <= result.getPages() && Checker.BeNotEmpty(result.getRecords())) {
            apiResult = genStatic(createId, result);
            pageNo += 1;
            pageContent(createId, pageNo);
        }
        return apiResult;
    }

    private ApiResult genStatic(String createId, IPage<ContentDto> page) {
        ApiResult apiResult = ApiResult.result();
        List<ContentDto> contentDtos = page.getRecords();
        if (Checker.BeNotEmpty(contentDtos)) {
            for (ContentDto contentDto : contentDtos) {
                String categoryCode = contentDto.getCategoryCode();
                String contentId = contentDto.getId();
                String tempPath = contentDto.getTemplatePath();
                if (Checker.BeBlank(tempPath) || Checker.BeBlank(categoryCode)) {
                    continue;
                }
                String dateStr = DateTimeFormatter.ofPattern(Constants.YMD_).format(LocalDateTime.now());
                boolean hasGen = Checker.BeNotBlank(contentDto.getRulesData()) && Checker.BeNotNull(contentDto.getHasStatic()) && contentDto.getHasStatic();
                if (hasGen) {
                    dateStr = contentDto.getRulesData();
                }
                String staticRootPath = thinkCmsConfig.getSiteStaticFileRootPath();
                String staticFilePath = File.separator + categoryCode + File.separator + dateStr + File.separator + contentId + Constants.DEFAULT_HTML_SUFFIX;
                ContentHelp contentHelp = new ContentHelp(contentDto);
                contentHelp.put(Constants.url, staticFilePath);
                staticFilePath = staticRootPath + staticFilePath;
                apiResult = templateComponent.createContentStaticFile(tempPath, staticFilePath, contentHelp);
                apiResult.put(SecurityConstants.USER_ID, createId);
                notifyFront(apiResult, page);
            }
        }
        return apiResult;
    }


}
