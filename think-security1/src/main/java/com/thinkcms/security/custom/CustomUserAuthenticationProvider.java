package com.thinkcms.security.custom;

import com.thinkcms.core.handler.CustomException;
import com.thinkcms.core.utils.Checker;
import com.thinkcms.system.api.system.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomUserAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    CustomUserLoginRiskCheck customUserLoginRiskCheck;
    @Autowired
    MenuService menuService;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails user = null;
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        try {
            user = this.customUserDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            this.customUserLoginRiskCheck.writeErrorLog(username);
            throw e;
        } catch (CustomException | org.springframework.security.authentication.LockedException e) {
            throw new DisabledException(e.getMessage());
        }
        if (!this.bCryptPasswordEncoder.matches(password, user.getPassword())) {
            this.customUserLoginRiskCheck.writeErrorLog(username);
            throw new DisabledException("账号或密码不正确!");
        }
        try {
            this.customUserLoginRiskCheck.checkerLicense();
        } catch (Exception e) {
            throw new DisabledException(Checker.BeNotNull(e.getCause()) ? e.getCause().getMessage() : e.getMessage());
        }

        return (Authentication) new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    }


    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}