package com.thinkcms.freemark.directives;

import com.thinkcms.core.constants.Constants;
import com.thinkcms.core.constants.DirectiveNameEnum;
import com.thinkcms.core.model.PageDto;
import com.thinkcms.core.utils.Checker;
import com.thinkcms.freemark.corelibs.directive.AbstractTemplateDirective;
import com.thinkcms.freemark.corelibs.handler.RenderHandler;
import com.thinkcms.service.api.category.CmsCategoryService;
import com.thinkcms.service.api.content.ContentService;
import com.thinkcms.service.dto.content.ContentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Transactional
@Component
public class CmsCategoryListDirective extends AbstractTemplateDirective {

    @Autowired
    ContentService contentService;

    @Autowired
    CmsCategoryService cmsCategoryService;

    @Override
    public void execute(RenderHandler handler) throws Exception {
        Integer pageNo = handler.getInteger(Constants.PAGE_NO);
        Integer pageSize = handler.getInteger(Constants.PAGE_SIZE);
        Integer pageCount = handler.getInteger(Constants.PAGE_COUNT);
        String categoryId = handler.getString(Constants.categoryId);
        if(Checker.BeNotNull(pageNo)){
            PageDto<ContentDto> pageDto =new PageDto<>();
            ContentDto contentDto =new ContentDto();
            contentDto.setCategoryId(categoryId);
            pageDto.setPageSize(pageSize).setPageNo(pageNo).setPageCount(pageCount).setDto(contentDto);
            List<ContentDto> contents=contentService.pageContentForCategoryGen(pageDto);
            handler.listToMap(this.getDirectiveName().getCode(),contents).render();
        }
    }

    @PostConstruct
    public void initName(){
        this.setDirectiveName(DirectiveNameEnum.CMS_CATEGORY_LIST_DIRECTIVE);
    }
}
