package com.thinkcms.web.controller.system;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.thinkcms.service.dto.category.CmsCategoryDto;
import com.thinkcms.core.annotation.Logs;
import com.thinkcms.core.enumerate.LogModule;
import com.thinkcms.core.enumerate.LogOperation;
import com.thinkcms.core.utils.Tree;
import com.thinkcms.web.controller.BaseController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thinkcms.service.api.category.OrgCategoryService;
import com.thinkcms.service.dto.category.OrgCategoryDto;
import com.thinkcms.core.model.PageDto;

/**
 * <p>
 * 部门分类 前端控制器
 * </p>
 *
 * @author LG
 * @since 2019-11-13
 */
@Validated
@RestController
@RequestMapping("sysOrgCategory")
public class OrgCategoryController extends BaseController<OrgCategoryService> {


    @GetMapping("getByPk")
    public OrgCategoryDto get(@NotBlank @RequestParam String id){
       return  service.getByPk(id);
    }

    @PostMapping(value="save")
    public void save(@Validated @RequestBody OrgCategoryDto v){
        service.insert(v);
    }

    @PutMapping("update")
    public void update(@RequestBody OrgCategoryDto v){
        service.updateByPk(v);
    }

    @DeleteMapping("deleteByPk")
    public boolean deleteByPk(@NotBlank @RequestParam String pk) {
         return service.deleteByPk(pk);
    }

    @DeleteMapping(value = "deleteByIds")
    public void deleteByPks(@NotEmpty @RequestBody List<String> ids){
          service.deleteByPks(ids);
    }

    @PostMapping("list")
    public  List<OrgCategoryDto> list(@RequestBody OrgCategoryDto v){
        return service.listDto(v);
    }

    @PostMapping("page")
    public PageDto<OrgCategoryDto> listPage(@RequestBody PageDto<OrgCategoryDto> pageDto){
        return service.listPage(pageDto);
    }

    @Logs(module = LogModule.ORG,operaEnum = LogOperation.SAVE,operation = "给组织分配栏目模块")
    @PostMapping(value="saveOrgCategory")
    public void saveOrgCategory(@RequestBody OrgCategoryDto v){
        service.saveOrgCategory(v);
    }


    //  部门分配菜单用
    @RequestMapping("/selectTreeCategoryByOrg")
    public Tree<CmsCategoryDto> selectTreeCategoryByOrg(@RequestParam String orgId) {
        Tree<CmsCategoryDto> treeCategorys = service.selectTreeCategoryByOrg(orgId);
        return treeCategorys;
    }

}
