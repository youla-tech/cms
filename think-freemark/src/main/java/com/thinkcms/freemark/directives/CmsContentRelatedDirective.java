package com.thinkcms.freemark.directives;

import com.thinkcms.core.constants.Constants;
import com.thinkcms.core.constants.DirectiveNameEnum;
import com.thinkcms.core.utils.Checker;
import com.thinkcms.freemark.corelibs.directive.AbstractTemplateDirective;
import com.thinkcms.freemark.corelibs.handler.RenderHandler;
import com.thinkcms.service.api.content.CmsContentRelatedService;
import com.thinkcms.service.dto.content.ContentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取相關内容
 */
@Component
public class CmsContentRelatedDirective extends AbstractTemplateDirective {

    @Autowired
    CmsContentRelatedService contentRelatedService;

    @Override
    public void execute(RenderHandler handler) throws IOException, Exception {
        String contentId = handler.getString(Constants.contentId);
        Integer  maxRowNum=handler.getInteger(Constants.rowNum);
        Boolean hasRelated = handler.getBoolean(Constants.hasRelated);
        List<ContentDto> contentDtos = new ArrayList<>(16);
        boolean search = Checker.BeNull(hasRelated) || (Checker.BeNotNull(hasRelated) && hasRelated);
        if(search){
            if(Checker.BeNotBlank(contentId)){
                contentDtos= contentRelatedService.getRelatedByContentId(contentId,Checker.BeNotNull(maxRowNum)? maxRowNum:50);
            }
        }
        handler.listToMap(getDirectiveName().getCode(),contentDtos).render();
    }

    @PostConstruct
    public void initName(){
        this.setDirectiveName(DirectiveNameEnum.CMS_CONTENT_RELATED_DIRECTIVE);
    }
}
