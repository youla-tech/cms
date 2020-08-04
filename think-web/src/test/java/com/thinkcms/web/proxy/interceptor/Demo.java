package com.thinkcms.web.proxy.interceptor;

import com.thinkcms.web.proxy.UserService;
import com.thinkcms.web.proxy.UserServiceImpl;
import org.junit.Test;

public class Demo {

    @Test
    public void tes(){
        UserService userService=(UserService)InterceptorProxy.genInterceptorProxy(new UserServiceImpl(),"com.org.lb.web.proxy.interceptor.MyInterceptor");
        userService.addUser("aaaaaa","1111111");
    }

}
