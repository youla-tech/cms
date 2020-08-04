package com.thinkcms.freemark.directives;

import com.thinkcms.core.constants.Constants;
import com.thinkcms.core.constants.DirectiveNameEnum;
import com.thinkcms.core.utils.Checker;
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
 * 查询分类id 查询单个内容
 */
@Component
public class CmsTopTagDirective extends AbstractTemplateDirective {

    @Autowired
    ContentService contentService;

    @Override
    public void execute(RenderHandler handler) throws IOException, Exception {
        String topTag = handler.getString(Constants.topTag);
        String categoryId = handler.getString(Constants.categoryId);
        Integer rowNum = handler.getInteger(Constants.rowNum);
        if (Checker.BeNotBlank(topTag)) {
            rowNum=Checker.BeNotNull(rowNum)?rowNum:10;
            List<ContentDto> contentDtos= contentService.getContentsByTopTag(topTag,rowNum,categoryId);
            handler.listToMap(getDirectiveName().getCode(),contentDtos).render();
        }
    }

    @PostConstruct
    public void initName() {
        this.setDirectiveName(DirectiveNameEnum.CMS_TOP_TAG_DIRECTIVE);
    }
}
