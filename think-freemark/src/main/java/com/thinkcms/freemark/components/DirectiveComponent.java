package com.thinkcms.freemark.components;
import com.thinkcms.core.config.ThinkCmsConfig;
import com.thinkcms.core.constants.Constants;
import com.thinkcms.core.utils.Checker;
import com.thinkcms.freemark.corelibs.directive.AbstractTemplateDirective;
import com.thinkcms.freemark.corelibs.handler.BaseMethod;
import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 指令组件(注册所有的指令组件)
 */
@Component
public class DirectiveComponent {


    @Autowired
    private TemplateComponent templateComponent;

    @Autowired
    private ThinkCmsConfig thinkCmsConfig;

    @Autowired
    public void init(Configuration configuration, List<AbstractTemplateDirective> templateDirectives, List<BaseMethod> baseMethodHandlers) throws TemplateModelException {
        Map<String, Object> freemarkerVariables = new HashMap<>();
        if(Checker.BeNotEmpty(templateDirectives)){
            for (AbstractTemplateDirective directive:templateDirectives){
                freemarkerVariables.put(directive.getDirectiveName().getValue(),directive);
            }
        }
        if(Checker.BeNotEmpty(baseMethodHandlers)){
            for (BaseMethod baseMethod:baseMethodHandlers){
                freemarkerVariables.put(baseMethod.getMethodName().getValue(),baseMethod);
            }
        }
        configuration.setAllSharedVariables(new SimpleHash(freemarkerVariables,configuration.getObjectWrapper()));
        configuration.setSharedVariable(Constants.DOMAIN, thinkCmsConfig.getSiteDomain());
        configuration.setSharedVariable(Constants.SERVER, thinkCmsConfig.getServerApi());
        templateComponent.setConfiguration(configuration);
    }
}
