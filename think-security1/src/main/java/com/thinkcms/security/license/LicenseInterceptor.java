package com.thinkcms.security.license;

import cn.hutool.core.io.FileUtil;
import com.thinkcms.core.utils.Checker;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class LicenseInterceptor implements Interceptor {
    public Map<String, Object> before(Object proxy, Object target, Method method, Object[] args) {
        Map<String, Object> params = new LinkedHashMap<>(16);
        String path = args[0].toString() + "/license.dat";
        boolean exist = FileUtil.exist(path);
        if (exist) {
            List<String> properties = FileUtil.readUtf8Lines(path);
            if (Checker.BeNotEmpty(properties).booleanValue()) {
                properties.forEach(propertie -> {
                    if (Checker.BeNotBlank(propertie).booleanValue()) {
                        String[] kv = propertie.split(":");
                        params.put(kv[0], kv[1]);
                    }
                });
            }
        }
        return params;
    }


    public void around(Object proxy, Object target, Method method, Object[] args) {
    }


    public void after(Object proxy, Object target, Method method, Object[] args, Map<String, Object> objectMap) {
        if (!objectMap.isEmpty() && target instanceof VerifyLicenseImpl) {
            VerifyLicenseImpl verifyLicense = (VerifyLicenseImpl) target;
            verifyLicense.baseRedisService.set("/license.dat", objectMap, Long.valueOf(TimeUnit.DAYS.toSeconds(10L)));
        }
    }
}
