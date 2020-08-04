package com.thinkcms.addons.service;
import com.thinkcms.system.api.system.UserService;
import java.util.Map;

public class OssServiceImpl extends BasePluginService implements  OssService{

    UserService userService= getBean(UserService.class);

    @Override
    public void test(Map<String,Object> params) {
        //userService.info();
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println("params:"+params.size());
    }
}
