package com.thinkcms.freemark.directives;

import com.thinkcms.core.constants.Constants;
import com.thinkcms.core.constants.DirectiveNameEnum;
import com.thinkcms.core.utils.Checker;
import com.thinkcms.freemark.corelibs.directive.AbstractTemplateDirective;
import com.thinkcms.freemark.corelibs.handler.RenderHandler;
import com.thinkcms.service.api.category.CmsCategoryService;
import com.thinkcms.service.dto.navigation.Navigation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Component
public class CmsNavigation extends AbstractTemplateDirective {

    @Autowired
    CmsCategoryService categoryService;

    @Override
    public void execute(RenderHandler handler) throws IOException, Exception {
        String contentId = handler.getString(Constants.contentId);
        if (Checker.BeNotBlank(contentId)) {
            List<Navigation> navigations = categoryService.getNavigation(contentId);
            handler.listToMap(getDirectiveName().getCode(),navigations).render();
        }
    }

    @PostConstruct
    public void initName(){
        this.setDirectiveName(DirectiveNameEnum.CMS_NAVIGATION);
    }
}
