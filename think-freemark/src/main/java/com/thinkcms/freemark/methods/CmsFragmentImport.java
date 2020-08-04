package com.thinkcms.freemark.methods;

import com.thinkcms.core.constants.MethodNameEnum;
import com.thinkcms.core.utils.Checker;
import com.thinkcms.freemark.corelibs.handler.BaseMethod;
import com.thinkcms.service.api.fragment.FragmentFileModelService;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class CmsFragmentImport extends BaseMethod {

    @Autowired
    FragmentFileModelService fragmentFileModelService;

    @Override
    public Object exec(List arguments) throws TemplateModelException {
        String code = getString(0, arguments);
        if (Checker.BeNotBlank(code)) {
            String fragmentPath = fragmentFileModelService.getFragmentFilePathByCode(code);
            if (Checker.BeNotBlank(fragmentPath)) {
                return fragmentPath;
            }
        }
        return null;
    }
    @PostConstruct
    public void init(){
        this.setMethodName(MethodNameEnum.LBCMS_FRAGMENT_IMPORT);
    }
}
