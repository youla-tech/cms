package com.thinkcms.web.controller;
import com.thinkcms.core.api.BaseService;
import com.thinkcms.core.utils.BaseContextKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;

public class BaseController <Service extends BaseService>{

    @Autowired
    protected Service service;

	@RequestMapping("delByPk")
    protected boolean delByPk(@NotBlank @RequestParam String pk){
        return service.deleteByPk(pk);
    }

    @RequestMapping("getByPk")
    protected Object getByPk(@NotBlank  String pk){
        return service.getByPk(pk);
    }

    protected  String getUserId(){
	    return BaseContextKit.getUserId();
    }
}