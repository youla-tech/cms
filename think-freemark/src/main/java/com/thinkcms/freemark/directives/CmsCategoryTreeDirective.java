package com.thinkcms.freemark.directives;

import com.thinkcms.core.constants.Constants;
import com.thinkcms.core.constants.DirectiveNameEnum;
import com.thinkcms.core.utils.Tree;
import com.thinkcms.freemark.corelibs.directive.AbstractTemplateDirective;
import com.thinkcms.freemark.corelibs.handler.RenderHandler;
import com.thinkcms.service.api.category.CmsCategoryService;
import com.thinkcms.service.dto.category.CmsCategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * 获取分类栏目首页展示
 */
@Component
public class CmsCategoryTreeDirective extends AbstractTemplateDirective {

    @Autowired
    CmsCategoryService cmsCategoryService;

    @Override
    public void execute(RenderHandler handler) throws IOException, Exception {
        String categoryId = handler.getString(Constants.categoryId);
        Tree<CmsCategoryDto> tree = cmsCategoryService.selectHomePageCategory(categoryId);
        handler.put(getDirectiveName().getCode(),tree.getChildren()).render();
    }

    @PostConstruct
    public void initName(){
        this.setDirectiveName(DirectiveNameEnum.CMS_CATEGORY_TREE_DIRECTIVE);
    }
}
