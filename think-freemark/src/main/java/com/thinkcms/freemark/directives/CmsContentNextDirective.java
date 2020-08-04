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
import java.util.Arrays;

/**
 * 查询分类id 查询单个内容
 */
@Component
public class CmsContentNextDirective extends AbstractTemplateDirective {

    @Autowired
    ContentService contentService;

    @Override
    public void execute(RenderHandler handler) throws IOException, Exception {
        String contentId = handler.getString(Constants.contentId);
        String categoryId = handler.getString(Constants.categoryId);
        if (Checker.BeNotBlank(contentId)) {
            ContentDto contentDto = contentService.getNextOrPreviousContentByIdAndCateg(contentId,categoryId,true);
            if(Checker.BeNull(contentDto)){
                ContentDto contentPreDto = contentService.getNextOrPreviousContentByIdAndCateg(contentId,categoryId,false);
                if(Checker.BeNotNull(contentPreDto)){
                    contentService.reStaticBatchGenCid(Arrays.asList(contentPreDto.getId()),"");
                }
            }
            handler.beanToMap(getDirectiveName().getCode(),contentDto).render();
        }
    }

    @PostConstruct
    public void initName() {
        this.setDirectiveName(DirectiveNameEnum.CMS_CONTENT_NEXT_DIRECTIVE);
    }
}
