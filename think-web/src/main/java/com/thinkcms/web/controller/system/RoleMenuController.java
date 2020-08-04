package com.thinkcms.web.controller.system;

import com.thinkcms.core.utils.Checker;
import com.thinkcms.system.api.system.RoleMenuService;
import com.thinkcms.system.dto.system.MenuDto;
import com.thinkcms.system.dto.system.RoleMenuDto;
import com.thinkcms.core.utils.ApiResult;
import com.thinkcms.core.utils.Tree;
import com.thinkcms.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色与菜单对应关系 前端控制器
 * </p>
 *
 * @author dl
 * @since 2018-03-23
 */
@RestController
@RequestMapping("/role/roleMenu")
public class RoleMenuController extends BaseController<RoleMenuService> {
    @Autowired
    RoleMenuService roleMenuService;

    @RequestMapping("/selectTreeMenuByRole")
    public Tree<MenuDto> selectTreeMenuByRole(@RequestParam String roleId) {
        Tree<MenuDto> menus = roleMenuService.selectTreeMenuByUser(roleId, getUserId());
        return menus;
    }

    @RequestMapping("/assignMenu")
    public ApiResult assignMenu(@RequestBody RoleMenuDto roleMenuDto) {
        if (Checker.BeEmpty(roleMenuDto.getOnCheckKeys()) || Checker.BeBlank(roleMenuDto.getRoleId())) {
            return ApiResult.result("参数异常!", -1);
        }
        boolean f = roleMenuService.assignMenu(roleMenuDto);
        if (f) {
            return ApiResult.result();
        } else
            return ApiResult.result("操作失败", -1);
    }
}
