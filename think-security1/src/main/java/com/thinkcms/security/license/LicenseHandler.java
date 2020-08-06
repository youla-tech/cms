package com.thinkcms.security.license;

import com.thinkcms.core.handler.CustomException;
import com.thinkcms.core.utils.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class LicenseHandler implements InvocationHandler {
    private static final Logger log = LoggerFactory.getLogger(LicenseHandler.class);


    private Object target;

    private Class cls;

    private Interceptor interceptor;


    public LicenseHandler() {
    }


    public LicenseHandler(Object target, Class<Interceptor> cls) throws Exception {
        this.target = target;
        this.cls = cls;
        this.interceptor = cls.newInstance();
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Map<String, Object> propertie = this.interceptor.before(proxy, this.target, method, args);
        if (propertie.isEmpty()) {
            log.error("20031:license证书不存在");
            throw new CustomException(ApiResult.result(20031));
        }
        Object object = method.invoke(this.target, new Object[]{args[0], propertie});
        this.interceptor.after(proxy, this.target, method, args, propertie);
        return object;
    }
}
