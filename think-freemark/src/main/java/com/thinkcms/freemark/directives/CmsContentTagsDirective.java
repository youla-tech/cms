package com.thinkcms.freemark.directives;

import com.thinkcms.core.constants.Constants;
import com.thinkcms.core.constants.DirectiveNameEnum;
import com.thinkcms.core.utils.Checker;
import com.thinkcms.freemark.corelibs.directive.AbstractTemplateDirective;
import com.thinkcms.freemark.corelibs.handler.RenderHandler;
import com.thinkcms.service.api.tags.CmsTagsService;
import com.thinkcms.service.dto.tags.CmsTagsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取内容标签
 */
@Component
public class CmsContentTagsDirective extends AbstractTemplateDirective {

    @Autowired
    CmsTagsService cmsTagsService;

    @Override
    public void execute(RenderHandler handler) throws IOException, Exception {
        String contentId = handler.getString(Constants.contentId);
        Boolean hasTag = handler.getBoolean(Constants.hasTag);
        List<CmsTagsDto> cmsTagsDtos = new ArrayList<>(16);
        boolean search = Checker.BeNull(hasTag) || (Checker.BeNotNull(hasTag) && hasTag);
        if(search){
            if(Checker.BeNotBlank(contentId)){
                cmsTagsDtos= cmsTagsService.getTagsByContentId(contentId);
            }
        }
        handler.listToMap(getDirectiveName().getCode(),cmsTagsDtos).render();
    }

    @PostConstruct
    public void initName(){
        this.setDirectiveName(DirectiveNameEnum.CMS_CONTENT_TAGS_DIRECTIVE);
    }
}
