package com.thinkcms.freemark.directives;

import com.thinkcms.core.constants.Constants;
import com.thinkcms.core.constants.DirectiveNameEnum;
import com.thinkcms.freemark.corelibs.directive.AbstractTemplateDirective;
import com.thinkcms.freemark.corelibs.handler.RenderHandler;
import com.thinkcms.service.api.category.CmsCategoryService;
import com.thinkcms.service.dto.category.CmsCategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

/**
 * 根据zi分类查询父分类指令
 */
@Component
public class CmsParentCategorysDirective extends AbstractTemplateDirective {

    @Autowired
    CmsCategoryService cmsCategoryService;

    @Override
    public void execute(RenderHandler handler) throws IOException, Exception {
        String id = handler.getString(Constants.categoryId);
        List<CmsCategoryDto> pcategorys = cmsCategoryService.selectParentCategorys(id);
        handler.listToMap(getDirectiveName().getCode(),pcategorys).render();
    }

    @PostConstruct
    public void initName(){
        this.setDirectiveName(DirectiveNameEnum.CMS_PARENT_CATEGORY_DIRECTIVE);
    }
}
