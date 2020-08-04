package com.thinkcms.web.controller.system;

import com.thinkcms.core.model.PageDto;
import com.thinkcms.core.utils.ApiResult;
import com.thinkcms.system.api.system.UserService;
import com.thinkcms.system.dto.system.UserDto;
import com.thinkcms.core.annotation.Logs;
import com.thinkcms.core.enumerate.LogModule;
import com.thinkcms.core.enumerate.LogOperation;
import com.thinkcms.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Validated
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<UserService> {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Logs(module =LogModule.USER,operation = "进入/刷新首页")
    @GetMapping("info")
    public UserDto info() {
        return service.info(getUserId());
    }

    @GetMapping("getById")
    public UserDto getById(@NotBlank(message = "id 不能为空") String id) {
        return service.getById(id);
    }

    @PostMapping("page")
    public PageDto<UserDto> page(@RequestBody PageDto<UserDto> userDto) {
        userDto.getDto().condition().notIn("id","1");
        return service.listPage(userDto);
    }


    @Logs(module =LogModule.USER,operaEnum = LogOperation.SAVE)
    @PostMapping("save")
    public boolean save(@RequestBody UserDto userDto) {
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        return service.save(userDto);
    }

    @Logs(module =LogModule.USER,operaEnum = LogOperation.UPDATE)
    @PostMapping("update")
    public boolean update(@Valid @RequestBody UserDto userDto) {
        return service.update(userDto);
    }


    @Logs(module =LogModule.USER,operaEnum = LogOperation.DELETE)
    @DeleteMapping("delete")
    public boolean delete(@NotBlank String id) {
        return service.deleteByUserId(id);
    }

    @PostMapping("batch")
    public boolean batch(@RequestBody UserDto userDto) {
        return service.batch(userDto.getType(), userDto.getUserIds());
    }

    @Logs(module =LogModule.USER,operation = "个人账户资料修改")
    @PutMapping("updateUserInfo")
    public boolean updateUserInfo(@RequestBody UserDto userDto) {
        return service.updateUserInfo(userDto);
    }

    @Logs(module = LogModule.USER,operation = "修改密码")
    @RequestMapping(value = "modifyPass", method=RequestMethod.PUT)
    public void modifyPass(@RequestBody UserDto user) {
        service.modifyPass(user);
    }

    @Logs(module =LogModule.USER,operation = "重置密码")
    @PutMapping("resetPass")
    public ApiResult resetPass(@NotBlank String id) {
        return service.resetPass(id);
    }

}
