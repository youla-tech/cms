/*    */ package com.thinkcms.security.custom;
/*    */ 
/*    */ import com.thinkcms.core.handler.CustomException;
/*    */ import com.thinkcms.core.utils.BaseContextKit;
/*    */ import com.thinkcms.core.utils.Checker;
/*    */ import com.thinkcms.core.utils.SpringContextHolder;
/*    */ import java.io.IOException;
/*    */ import javax.servlet.FilterChain;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.ServletRequest;
/*    */ import javax.servlet.ServletResponse;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.security.access.AccessDeniedException;
/*    */ import org.springframework.security.core.Authentication;
/*    */ import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
/*    */ import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
/*    */ import org.springframework.security.web.util.matcher.RequestMatcher;
/*    */ import org.springframework.web.filter.OncePerRequestFilter;
/*    */ 
/*    */ public class CustomJwtAuthenticationFilter
/*    */   extends OncePerRequestFilter {
/*    */   private RequestMatcher matcher;
/*    */   private JwtTokenStore tokenStore;
/*    */   private AbsCustomJwtHandler customJwtHandler;
/*    */   
/*    */   public void setMatcher(RequestMatcher matcher) {
/* 28 */     this.matcher = matcher; } public void setTokenStore(JwtTokenStore tokenStore) { this.tokenStore = tokenStore; } public void setCustomJwtHandler(AbsCustomJwtHandler customJwtHandler) { this.customJwtHandler = customJwtHandler; } public void setTokenExtractor(TokenExtractor tokenExtractor) { this.tokenExtractor = tokenExtractor; } public boolean equals(Object o) { if (o == this) return true;  if (!(o instanceof CustomJwtAuthenticationFilter)) return false;  CustomJwtAuthenticationFilter other = (CustomJwtAuthenticationFilter)o; if (!other.canEqual(this)) return false;  Object this$matcher = getMatcher(), other$matcher = other.getMatcher(); if ((this$matcher == null) ? (other$matcher != null) : !this$matcher.equals(other$matcher)) return false;  Object this$tokenStore = getTokenStore(), other$tokenStore = other.getTokenStore(); if ((this$tokenStore == null) ? (other$tokenStore != null) : !this$tokenStore.equals(other$tokenStore)) return false;  Object this$customJwtHandler = getCustomJwtHandler(), other$customJwtHandler = other.getCustomJwtHandler(); if ((this$customJwtHandler == null) ? (other$customJwtHandler != null) : !this$customJwtHandler.equals(other$customJwtHandler)) return false;  Object this$tokenExtractor = getTokenExtractor(), other$tokenExtractor = other.getTokenExtractor(); return !((this$tokenExtractor == null) ? (other$tokenExtractor != null) : !this$tokenExtractor.equals(other$tokenExtractor)); } protected boolean canEqual(Object other) { return other instanceof CustomJwtAuthenticationFilter; } public int hashCode() { int PRIME = 59; result = 1; Object $matcher = getMatcher(); result = result * 59 + (($matcher == null) ? 43 : $matcher.hashCode()); Object $tokenStore = getTokenStore(); result = result * 59 + (($tokenStore == null) ? 43 : $tokenStore.hashCode()); Object $customJwtHandler = getCustomJwtHandler(); result = result * 59 + (($customJwtHandler == null) ? 43 : $customJwtHandler.hashCode()); Object $tokenExtractor = getTokenExtractor(); return result * 59 + (($tokenExtractor == null) ? 43 : $tokenExtractor.hashCode()); } public String toString() { return "CustomJwtAuthenticationFilter(matcher=" + getMatcher() + ", tokenStore=" + getTokenStore() + ", customJwtHandler=" + getCustomJwtHandler() + ", tokenExtractor=" + getTokenExtractor() + ")"; }
/*    */   
/*    */   public RequestMatcher getMatcher() {
/* 31 */     return this.matcher;
/*    */   } public JwtTokenStore getTokenStore() {
/* 33 */     return this.tokenStore;
/*    */   } public AbsCustomJwtHandler getCustomJwtHandler() {
/* 35 */     return this.customJwtHandler;
/*    */   }
/* 37 */   TokenExtractor tokenExtractor = (TokenExtractor)SpringContextHolder.getBean(TokenExtractor.class); public TokenExtractor getTokenExtractor() { return this.tokenExtractor; }
/*    */   
/*    */   public CustomJwtAuthenticationFilter(RequestMatcher matcher, AbsCustomJwtHandler customJwtHandler) {
/* 40 */     this.matcher = matcher;
/* 41 */     this.customJwtHandler = customJwtHandler;
/*    */   }
/*    */   public CustomJwtAuthenticationFilter(RequestMatcher matcher, JwtTokenStore tokenStore, AbsCustomJwtHandler customJwtHandler) {
/* 44 */     this.matcher = matcher;
/* 45 */     this.tokenStore = tokenStore;
/* 46 */     this.customJwtHandler = customJwtHandler;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
/* 54 */     Authentication authentication = this.tokenExtractor.extract(request);
/* 55 */     if (Checker.BeNull(authentication)) {
/* 56 */       throw new AccessDeniedException("Invalid Jwt is invalid!");
/*    */     }
/*    */     try {
/* 59 */       this.customJwtHandler.handlerJwtToken(authentication, this.tokenStore);
/* 60 */     } catch (CustomException error) {
/* 61 */       throw new AccessDeniedException(error.getMessage());
/*    */     } 
/*    */     try {
/* 64 */       filterChain.doFilter((ServletRequest)request, (ServletResponse)response);
/*    */     } finally {
/* 66 */       BaseContextKit.remove();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\kezai_su\Downloads\think-security-1.0.3.jar!\com\thinkcms\security\custom\CustomJwtAuthenticationFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */