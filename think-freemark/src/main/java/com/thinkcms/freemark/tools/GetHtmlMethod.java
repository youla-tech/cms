package com.thinkcms.freemark.tools;
import com.thinkcms.core.constants.MethodNameEnum;
import com.thinkcms.freemark.corelibs.handler.BaseMethod;
import freemarker.template.TemplateModelException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 *
 * GetHtmlMethod
 * 
 */
@Component
public class GetHtmlMethod extends BaseMethod {

    //  CategorySubject categorySubject;

    @SuppressWarnings("unchecked")
    @Override
    public Object exec(@SuppressWarnings("rawtypes") List arguments) throws TemplateModelException {
        return null;
    }

    @PostConstruct
    public void init(){
        this.setMethodName(MethodNameEnum.LBCMS_METHOD_PAGE);
    }

}
