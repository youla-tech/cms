package com.thinkcms.security.config;

import com.thinkcms.security.custom.CustomAccessDecisionManager;
import com.thinkcms.security.custom.CustomAccessDeniedHandler;
import com.thinkcms.security.custom.CustomAuthenDeniedEntryPoint;
import com.thinkcms.security.custom.CustomJwtAccessTokenConverter;
import com.thinkcms.security.custom.CustomJwtTokenStore;
import com.thinkcms.security.custom.CustomSecurityMetadataSource;
import com.thinkcms.security.custom.CustomTokenEnhancer;
import com.thinkcms.security.custom.CustomUserAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class AuthBeanConfig
{
  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
     return new BCryptPasswordEncoder();
  }
  
  @Bean
  public JwtAccessTokenConverter jwtAccessTokenConverter() {
     CustomJwtAccessTokenConverter jwtAccessTokenConverter = new CustomJwtAccessTokenConverter();
     jwtAccessTokenConverter.setSigningKey("j83jxnjsleubf73fdsEWrtsduids");
     return (JwtAccessTokenConverter)jwtAccessTokenConverter;
  }
  
  @Bean
  public TokenEnhancer tokenEnhancer() {
     return (TokenEnhancer)new CustomTokenEnhancer();
  }
  
  @Bean
  AccessDecisionManager accessDecisionManager() {
     return (AccessDecisionManager)new CustomAccessDecisionManager();
  }
  
  @Bean
  public DefaultTokenServices tokenServices() {
     DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
     defaultTokenServices.setReuseRefreshToken(true);
     defaultTokenServices.setSupportRefreshToken(true);
     defaultTokenServices.setTokenStore(tokenStore());
     return defaultTokenServices;
  }
  
  @Bean
  public TokenStore tokenStore() {
     return (TokenStore)new CustomJwtTokenStore(jwtAccessTokenConverter());
  }
  
  @Bean
  TokenExtractor tokenExtractor() {
     return (TokenExtractor)new BearerTokenExtractor();
  }
  
  @Bean
  AuthenticationEntryPoint authenticationEntryPoint() {
     return (AuthenticationEntryPoint)new CustomAuthenDeniedEntryPoint();
  }
  
  @Bean
  AccessDeniedHandler accessDeniedHandler() {
     return (AccessDeniedHandler)new CustomAccessDeniedHandler();
  }
  
  @Bean
  CustomSecurityMetadataSource customSecurityMetadataSource() {
     return new CustomSecurityMetadataSource();
  }
  
  @Bean
  public AuthenticationProvider authenticationProvider() {
     return (AuthenticationProvider)new CustomUserAuthenticationProvider();
  }
}