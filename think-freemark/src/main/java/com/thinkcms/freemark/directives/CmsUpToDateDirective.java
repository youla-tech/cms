package com.thinkcms.freemark.directives;

import com.thinkcms.core.constants.Constants;
import com.thinkcms.core.constants.DirectiveNameEnum;
import com.thinkcms.freemark.corelibs.directive.AbstractTemplateDirective;
import com.thinkcms.freemark.corelibs.handler.RenderHandler;
import com.thinkcms.service.api.content.ContentService;
import com.thinkcms.service.dto.content.ContentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

/**
 * 获取最新的内容
 */
@Component
public class CmsUpToDateDirective extends AbstractTemplateDirective {

    @Autowired
    ContentService contentService;

    @Override
    public void execute(RenderHandler handler) throws IOException, Exception {
        Integer  rowNum=handler.getInteger(Constants.rowNum);
        String categoryId = handler.getString(Constants.categoryId);
        List<ContentDto> contentDtos=contentService.getTopContent(DirectiveNameEnum.CMS_UPTODATE_DIRECTIVE,rowNum,categoryId);
        handler.listToMap(getDirectiveName().getCode(),contentDtos).render();
    }

    @PostConstruct
    public void initName(){
        this.setDirectiveName(DirectiveNameEnum.CMS_UPTODATE_DIRECTIVE);
    }
}
