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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private static final Logger log = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);


    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        log.error("------------用户权限异常-------------");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        WebUtil.write(response, ApiResult.result(HttpStatus.FORBIDDEN.value(), e.getMessage()));
    }
}

