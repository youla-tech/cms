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
import java.util.List;

/**
 * 根据code 获取分类栏目
 */
@Component
public class CmsCategoryByCodeDirective extends AbstractTemplateDirective {

    @Autowired
    CmsCategoryService cmsCategoryService;


    @Override
    public void execute(RenderHandler handler) throws  Exception {
        String[] codes= handler.getStringArray(Constants.codes);
        List<CmsCategoryDto> categorys=cmsCategoryService.selectCategoryByCodes(codes);
        handler.listToMap(getDirectiveName().getCode(),categorys).render();
    }
    @PostConstruct
    public void initName(){
        this.setDirectiveName(DirectiveNameEnum.CMS_CATEGORY_BY_CODE_DIRECTIVE);
    }
}
