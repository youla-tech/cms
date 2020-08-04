package com.thinkcms.addons.service;

import com.thinkcms.core.annotation.AddonInterceptor;
import com.thinkcms.core.annotation.AddonsMapping;

import java.util.Map;

public interface OssService  {

    @AddonsMapping(name = "test")
    @AddonInterceptor
    public void test(Map<String,Object> params);


}
