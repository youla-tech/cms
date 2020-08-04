package com.thinkcms.freemark.directives;

import com.thinkcms.core.constants.DirectiveNameEnum;
import com.thinkcms.freemark.corelibs.directive.AbstractTemplateDirective;
import com.thinkcms.freemark.corelibs.handler.RenderHandler;
import com.thinkcms.service.api.site.CmsSiteService;
import com.thinkcms.service.dto.site.CmsSiteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * 获取内容标签
 */
@Component
public class CmsSiteDirective extends AbstractTemplateDirective {

    @Autowired
    CmsSiteService cmsSiteService;

    @Override
    public void execute(RenderHandler handler) throws IOException, Exception {
        CmsSiteDto cmsSiteDto= cmsSiteService.getSite();
        handler.beanToMap(cmsSiteDto).render();
    }

    @PostConstruct
    public void initName(){
        this.setDirectiveName(DirectiveNameEnum.CMS_SITE_DIRECTIVE);
    }
}
