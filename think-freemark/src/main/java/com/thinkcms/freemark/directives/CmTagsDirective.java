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
import java.util.List;

/**
 * 获取内容标签
 */
@Component
public class CmTagsDirective extends AbstractTemplateDirective {

    @Autowired
    CmsTagsService cmsTagsService;

    @Override
    public void execute(RenderHandler handler) throws IOException, Exception {
        Integer  maxRowNum=handler.getInteger(Constants.rowNum);
        if(Checker.BeNull(maxRowNum)){
            maxRowNum = 10;
        }
        List<CmsTagsDto> cmsTagsDtos=cmsTagsService.listTags(maxRowNum);
        handler.listToMap(getDirectiveName().getCode(),cmsTagsDtos).render();
    }
    @PostConstruct
    public void initName(){
        this.setDirectiveName(DirectiveNameEnum.CMS_TAGS_DIRECTIVE);
    }
}
