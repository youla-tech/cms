package com.thinkcms.addons.core;
import com.thinkcms.addons.interceptor.AInterceptor;
import com.thinkcms.addons.service.OssService;
import com.thinkcms.addons.service.OssServiceImpl;
import com.thinkcms.addons.service.PluginService;
import java.util.Map;

public class OssPlugin extends PluginService {

    @Override
    public Object execute(String api, String requestMethod, Map<String,Object> params) {
        try {
            OssService ossService=getService(OssServiceImpl.class,new AInterceptor());
            return executeMethod(ossService,api,requestMethod,params);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }

}
