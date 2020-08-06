package com.thinkcms.security.custom;

import com.thinkcms.core.utils.BaseContextKit;
import com.thinkcms.system.api.system.MenuService;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {
    @Autowired
    MenuService menuService;

    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        Set<String> authPerms = this.menuService.selectPermsByUid(BaseContextKit.getUserId());
        Set<String> needAuthSets = (Set<String>) configAttributes.stream().map(auth -> auth.getAttribute()).collect(Collectors.toSet());
        boolean beMatch = authPerms.containsAll(needAuthSets);
        if (!beMatch) {
            throw new AccessDeniedException("用户权限不足,请联系管理员");
        }
    }


    public boolean supports(ConfigAttribute attribute) {
        return true;
    }


    public boolean supports(Class<?> clazz) {
        return true;
    }
}
