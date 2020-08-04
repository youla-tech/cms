package com.thinkcms.freemark.observers;
import com.thinkcms.core.api.BaseSolrService;
import com.thinkcms.core.config.ThinkCmsConfig;
import com.thinkcms.core.constants.Constants;
import com.thinkcms.core.constants.SolrCoreEnum;
import com.thinkcms.core.handler.CustomException;
import com.thinkcms.core.model.PageKeyWord;
import com.thinkcms.core.utils.ApiResult;
import com.thinkcms.core.utils.Checker;
import com.thinkcms.core.utils.SecurityConstants;
import com.thinkcms.freemark.components.TemplateComponent;
import com.thinkcms.freemark.corelibs.notify.AbstractNotify;
import com.thinkcms.freemark.corelibs.observer.AdapterObserver;
import com.thinkcms.freemark.corelibs.observer.ObserverAction;
import com.thinkcms.freemark.corelibs.observer.ObserverData;
import com.thinkcms.freemark.corelibs.observer.data.ContentCreateObserverData;
import com.thinkcms.service.api.content.ContentService;
import com.thinkcms.service.dto.content.ContentDto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Data
@Component
@Slf4j
public class ContentObserver extends AdapterObserver {

    @Autowired
    TemplateComponent templateComponent;

    @Autowired
    ContentService contentService;

    @Autowired
    ThinkCmsConfig thinkCmsConfig;

    @Autowired
    BaseSolrService baseSolrService;

    private AbstractNotify notifyRes;

    @Override
    public void update(ObserverData data) {
        // 组装数据 创建静态页
        if (Checker.BeNotNull(data)) {
            if (ObserverAction.valueOf(data.getObserverAction().getCode()).equals(ObserverAction.CONTENT_CREATE)) {
                if (data instanceof ContentCreateObserverData) {
                    ContentCreateObserverData obsData = (ContentCreateObserverData) data;
                    String dateStr = (obsData.getHasStatic() && Checker.BeNotBlank(obsData.getRulesData())) ? obsData.getRulesData(): data.getDateStr();
                    ApiResult apiResult = genStaticContentFile(obsData, dateStr);
                    if (!apiResult.ckSuccess()) {
                        throw new CustomException(apiResult);
                    }
                    updateDb(data, apiResult, dateStr);
                    if(Checker.BeNotNull(notifyRes))
                    notifyRes.notifyOnceSuccess(apiResult);
                }

            }
        }
    }

    @Override
    public void update(ObserverData data, AbstractNotify notifyRes) {
        // 组装数据 创建静态页
        this.notifyRes = notifyRes;
        try {
            update(data);
        }finally {
            this.notifyRes = null;
        }
    }

    private ApiResult genStaticContentFile(ContentCreateObserverData obsData, String dateStr) {
        String contentId = obsData.getContentId();
        String tempPath = obsData.getTempPath();
        PageKeyWord pageKeyWord = obsData.getPageKeyWord();
        if (Checker.BeNotBlank(tempPath)) { // 不存在内容模板时 不创建静态页
            String categoryId = obsData.getCategoryId();
            String categoryCode = obsData.getCategoryCode();
            String staticRootPath = thinkCmsConfig.getSiteStaticFileRootPath();
            String staticFilePath = File.separator + categoryCode + File.separator + dateStr + File.separator + contentId + Constants.DEFAULT_HTML_SUFFIX;
            Map<String, Object> params = new HashMap<>(16);
            params.put(Constants.contentId, contentId);
            params.put(Constants.categoryId, categoryId);
            params.put(Constants.url, staticFilePath);
            Map<String, Object> mapData= obsData.getMapData();
            if(Checker.BeNotEmpty(mapData))params.putAll(mapData);
            if (Checker.BeNotNull(pageKeyWord)) {
                params.put(Constants.title, Checker.BeNotBlank(pageKeyWord.getTitle()) ? pageKeyWord.getTitle() : "");
                params.put(Constants.keywords, Checker.BeNotBlank(pageKeyWord.getKeywords()) ? pageKeyWord.getKeywords() : "");
                params.put(Constants.description, Checker.BeNotBlank(pageKeyWord.getDescription()) ? pageKeyWord.getDescription() : "");
            }
            if(Checker.BeNotEmpty(obsData.getMapData())){
                params.putAll(obsData.getMapData());
            }
            staticFilePath = staticRootPath + staticFilePath;
            ApiResult apiResult= templateComponent.createContentStaticFile(tempPath, staticFilePath, params);
            if(Checker.BeNotNull(notifyRes)){
                apiResult.put(Constants.progress,100);
                apiResult.put(SecurityConstants.USER_ID,obsData.getCreateId());
            }
            return apiResult;
        }
        return ApiResult.result(-1);
    }

    private void updateDb(ObserverData data, ApiResult apiResult, String dateStr) {
        if (data instanceof ContentCreateObserverData) {
            ContentCreateObserverData contentObserverData = (ContentCreateObserverData) data;
            ContentDto contentDto = contentService.getByPk(contentObserverData.getContentId());
            contentDto.setHasStatic(true).setUrl(apiResult.get(Constants.url).toString()).setExtendParam(contentObserverData.getMapData())
            .setRulesData(dateStr);
            contentService.updateByPk(contentDto);
            baseSolrService.pushToSolr(SolrCoreEnum.DEFAULT_CORE,contentDto);
        }
    }
}
