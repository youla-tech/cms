package com.thinkcms.web.config;

import com.thinkcms.core.config.ThinkCmsConfig;
import com.thinkcms.core.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig  implements WebMvcConfigurer {

    @Autowired
    AuthInterceptor authInterceptor;

    @Autowired
    ThinkCmsConfig thinkCmsConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(authInterceptor).addPathPatterns("/**");
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {//添加资源全局拦截器
        registry.addResourceHandler(Constants.localtionUploadPattern+"**").addResourceLocations("file:///"+thinkCmsConfig.getFileResourcePath());
        //registry.addResourceHandler(Constants.localtionUploadPattern+"**").addResourceLocations("file:E:/blog");

    }
}
