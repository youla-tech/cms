package com.thinkcms.security.custom;

import com.thinkcms.core.utils.ApiResult;
import com.thinkcms.core.utils.WebUtil;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthenDeniedEntryPoint implements AuthenticationEntryPoint {
     private static final Logger log = LoggerFactory.getLogger(CustomAuthenDeniedEntryPoint.class);


    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.error("-----------登录失效--------------");
    
        WebUtil.write(response, ApiResult.result(HttpStatus.UNAUTHORIZED.value(), e.getMessage()));
    }
}
