package com.thinkcms.freemark.directives;

import com.thinkcms.core.constants.Constants;
import com.thinkcms.core.constants.DirectiveNameEnum;
import com.thinkcms.freemark.corelibs.directive.AbstractTemplateDirective;
import com.thinkcms.freemark.corelibs.handler.RenderHandler;
import com.thinkcms.service.api.category.CmsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;

/**
 * 根据父分类查询子分类指令
 */
@Component
public class CmsChildCategorysDirective extends AbstractTemplateDirective {

    @Autowired
    CmsCategoryService cmsCategoryService;

    @Override
    public void execute(RenderHandler handler) throws IOException, Exception {
        String id = handler.getString(Constants.categoryId);
        Map<String,Object> childCategorys = cmsCategoryService.selectChildCategorys(id);
        handler.putToMap(childCategorys).render();
    }

    @PostConstruct
    public void initName(){
        this.setDirectiveName(DirectiveNameEnum.CMS_CHILD_CATEGORY_DIRECTIVE);
    }
}
