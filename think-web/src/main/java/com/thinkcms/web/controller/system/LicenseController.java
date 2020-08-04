package com.thinkcms.web.controller.system;

import com.thinkcms.core.model.LicenseProperties;
import com.thinkcms.core.model.PageDto;
import com.thinkcms.core.utils.ApiResult;
import com.thinkcms.system.api.system.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author DL
 * @since 2018-03-19
 */
@RestController
@RequestMapping("/license")
public class LicenseController  {

    @Autowired
    LicenseService service;

    @RequestMapping("page")
    public PageDto<LicenseProperties> page(@RequestBody PageDto<LicenseProperties> roleDto) {
        return service.listPage(roleDto);
    }


    @PostMapping("/importLicense")
    public ApiResult importLicense(@RequestParam("file") MultipartFile file) {
        return service.importLicense(file);
    }

}
