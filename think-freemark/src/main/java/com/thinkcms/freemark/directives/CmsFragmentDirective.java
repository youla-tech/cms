package com.thinkcms.freemark.directives;

import com.thinkcms.core.constants.Constants;
import com.thinkcms.core.constants.DirectiveNameEnum;
import com.thinkcms.freemark.corelibs.directive.AbstractTemplateDirective;
import com.thinkcms.freemark.corelibs.handler.RenderHandler;
import com.thinkcms.service.api.fragment.FragmentService;
import com.thinkcms.service.dto.fragment.FragmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Component
public class CmsFragmentDirective extends AbstractTemplateDirective {

    @Autowired
    FragmentService fragmentService;

    @Override
    public void execute(RenderHandler handler) throws IOException, Exception {
        String code=handler.getString(Constants.code);
        List<FragmentDto> fragmentDtos=fragmentService.getFragmentDataByCode(code);
        handler.listToMap(this.getDirectiveName().getCode(),fragmentDtos).render();
    }

    @PostConstruct
    public void initName(){
        this.setDirectiveName(DirectiveNameEnum.CMS_FRAGMENT_DATA_DIRECTIVE);
    }
}
