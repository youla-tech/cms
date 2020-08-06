package com.thinkcms.security.custom;

import com.google.common.collect.Sets;
import com.thinkcms.core.utils.Checker;
import com.thinkcms.security.properties.PermitAllUrlProperties;
import com.thinkcms.system.api.system.MenuService;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;


public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    MenuService menuService;
    @Autowired
    PermitAllUrlProperties permitAllUrlProperties;
    @Value("${spring.application.name}")
    public String application;

    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String url = ((FilterInvocation) object).getHttpRequest().getServletPath();
        Collection<ConfigAttribute> atts = Sets.newHashSet();
    
        boolean authc = this.permitAllUrlProperties.ckTheSameApi(this.application, url);
        if (authc) {
            atts.add(new SecurityConfig("NOT_REQUIRED_HAVE_PERM"));
            return atts;
        }
        Set<String> perms = this.menuService.selectPermsByUrl(url);
        if (Checker.BeEmpty(perms).booleanValue()) {
            SecurityConfig securityConfig = new SecurityConfig("NOT_REQUIRED_HAVE_PERM");
            atts.add(securityConfig);
        } else {
            perms.stream().forEach(perm -> {
                if (Checker.BeNotBlank(perm).booleanValue()) {
                    SecurityConfig securityConfig = new SecurityConfig(perm);
                    atts.add(securityConfig);
                }
            });
        }
        return atts;
    }


    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }


    public boolean supports(Class<?> clazz) {
        return false;
    }
}

