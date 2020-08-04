package com.thinkcms.web.Interceptors;

import com.thinkcms.core.constants.MinAppConstant;
import com.thinkcms.core.utils.Checker;
import com.thinkcms.core.utils.SecurityConstants;
import com.thinkcms.core.utils.SpringContextHolder;
import com.thinkcms.security.custom.CustomSocketPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.security.Principal;
import java.util.Map;

/**
 * 检查握手请求和响应, 对WebSocketHandler传递属性
 */
@Slf4j
public class HandleShakeInterceptors implements HandshakeInterceptor {

    TokenStore tokenStore = SpringContextHolder.getBean(TokenStore.class);

    /**
     * 在握手之前执行该方法, 继续握手返回true, 中断握手返回false.
     * 通过attributes参数设置WebSocketSession的属性
     *
     * @param request
     * @param response
     * @param wsHandler
     * @param attributes
     * @return
     * @throws Exception
     */

    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        //保存客户端标识
        ServletServerHttpRequest req = (ServletServerHttpRequest) request;
        String token = req.getServletRequest().getParameter("token");
        if(Checker.BeBlank(token)){
            log.error("token can't be null websocket权限拒绝");
            return false;
        }
        Principal principal=ckToken(token);
        if(Checker.BeNull(principal)){
            log.error("token 无效 websocket权限拒绝");
            return false;
        }
        attributes.put("socketUser", principal);
        return true;
    }

    /**
     * 在握手之后执行该方法. 无论是否握手成功都指明了响应状态码和相应头.
     *
     * @param request
     * @param response
     * @param wsHandler
     * @param exception
     */
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {

        System.out.println(exception);
    }

    private Principal ckToken(String tokenValue) {
        if (Checker.BeNotBlank(tokenValue)) {
            OAuth2Authentication oauth=tokenStore.readAuthentication(tokenValue);
            Map<String, Object> details = (Map<String, Object>)oauth.getDetails();
            String userId = Checker.BeNull(details.get(MinAppConstant.USERID)) ? null : details.get(MinAppConstant.USERID).toString();
            String userName = Checker.BeNull(details.get(SecurityConstants.USER_NAME)) ? null : details.get(SecurityConstants.USER_NAME).toString();
            //设置当前访问器的认证用户
            CustomSocketPrincipal customPrincipal=new CustomSocketPrincipal(userId,userName);
            return customPrincipal;
        }
        return null;
    }
}