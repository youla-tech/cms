package com.thinkcms.web.controller.system;

import com.thinkcms.core.model.PageDto;
import com.thinkcms.system.api.system.OrgService;
import com.thinkcms.system.api.system.RoleService;
import com.thinkcms.system.api.system.UserRoleService;
import com.thinkcms.system.dto.system.OrgDto;
import com.thinkcms.core.enumerate.LogModule;
import com.thinkcms.core.enumerate.LogOperation;
import com.thinkcms.core.annotation.Logs;
import com.thinkcms.core.utils.Tree;
import com.thinkcms.web.controller.BaseController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

@Validated
@RequestMapping(value={"org"})
@RestController
public class OrgController extends BaseController<OrgService> {

	@Resource
	private RoleService roleService;
	
	@Resource
	private UserRoleService userRoleService;
	
	@Resource
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	@Logs(module = LogModule.ORG,operaEnum = LogOperation.VIEW)
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public PageDto<OrgDto> list(@RequestBody  PageDto<OrgDto> pageDto) {
		return service.listPage(pageDto);
	}


	@Logs(module = LogModule.ORG,operaEnum = LogOperation.SAVE)
	@RequestMapping(value="save",method=RequestMethod.POST)
	public void save(@Validated @RequestBody  OrgDto orgDto) {
		service.saveOrg(orgDto);
    }

	@RequestMapping(value="info",method = RequestMethod.GET)
	public OrgDto info(@NotBlank @RequestParam String id){
		return service.info(id);
	}

	@RequestMapping("/selectTreeList")
	public Tree<OrgDto> selectTreeList(){
		Tree<OrgDto> menus=service.selectTreeList();
		return menus;
	}


	@Logs(module = LogModule.ORG,operaEnum = LogOperation.UPDATE)
	@RequestMapping(value="update",method=RequestMethod.PUT)
	public void update(@Validated @RequestBody OrgDto orgDto) {
		service.updateByPk(orgDto);
	}


	@Logs(module = LogModule.ORG,operaEnum = LogOperation.DELETE)
	@DeleteMapping(value="delete")
	public boolean delByPk(@NotBlank @RequestParam String id){
		return service.deleteOrg(id);
	}

}
