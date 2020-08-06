package com.thinkcms.security.license;

import com.thinkcms.core.utils.Checker;

import java.lang.reflect.Proxy;

public class VerifyLicenseGenProxy {
    private static Object proxyObject;

    public static Object proxyVerifyLicense(Object target) throws Exception {
        if (Checker.BeNull(proxyObject)) {
            proxyObject = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new LicenseHandler(target, LicenseInterceptor.class));
        }
        return proxyObject;
    }
}
