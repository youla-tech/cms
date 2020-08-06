package com.thinkcms.security.config;

import com.thinkcms.core.utils.Checker;
import com.thinkcms.security.custom.CustomUserAuthenticationProvider;
import com.thinkcms.security.properties.PermitAllUrlProperties;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

@Configuration
@EnableWebSecurity
public class GloableSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    CustomUserAuthenticationProvider customUserAuthenticationProvider;
    @Autowired
    PermitAllUrlProperties permitAllUrlProperties;

    public void configure(WebSecurity web) throws Exception {
        List<String> ignores = this.permitAllUrlProperties.ignores();
        if (Checker.BeNotEmpty(ignores).booleanValue()) {
            web.ignoring().antMatchers(ignores.<String>toArray(new String[ignores.size()]));
        }
    }


    protected void configure(HttpSecurity http) throws Exception {
        ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) ((HttpSecurity.RequestMatcherConfigurer) http
                .requestMatchers().anyRequest())
                .and()
                .authorizeRequests()
                .antMatchers(new String[]{"/oauth/*", "/user/info", "/minapp/**", "/error", "/content/searchKeyWord"})).permitAll();
    }
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authenticationManager();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider((AuthenticationProvider) this.customUserAuthenticationProvider);
    }
}
