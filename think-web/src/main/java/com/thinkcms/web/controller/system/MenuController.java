package com.thinkcms.web.controller.system;

import com.thinkcms.system.api.system.MenuService;
import com.thinkcms.system.dto.system.MenuDto;
import com.thinkcms.core.utils.Tree;
import com.thinkcms.web.controller.BaseController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单管理 前端控制器
 * </p>
 *
 * @author dl
 * @since 2018-03-21
 */
@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController<MenuService> {

    @RequestMapping("/list")
    public List<MenuDto> selectList(MenuDto menu) {
        List<MenuDto> menus = service.listDto(menu);
        return menus;
    }

    @RequestMapping("info")
    public MenuDto info(@RequestParam String id) {
        return service.info(id);
    }

    @PostMapping("/save")
    public boolean save(@RequestBody MenuDto menuDto) {
        return service.save(menuDto);
    }

    @PostMapping("/update")
    public boolean update(@RequestBody MenuDto menuDto) {
        return service.update(menuDto);
    }

    @DeleteMapping("/delete")
    public boolean delete(String id) {
        return service.delete(id);
    }

    @RequestMapping("/selectTreeList")
    public Tree<MenuDto> selectTreeList() {
        Tree<MenuDto> menus = service.selectTreeList();
        return menus;
    }

}
