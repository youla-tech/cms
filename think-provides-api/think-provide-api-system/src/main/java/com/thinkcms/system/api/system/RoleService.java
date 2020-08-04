package com.thinkcms.system.api.system;
import com.thinkcms.core.api.BaseService;
import com.thinkcms.system.dto.system.RoleDto;
import com.thinkcms.core.utils.ApiResult;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author dl
 * @since 2018-03-19
 */
public interface RoleService extends BaseService<RoleDto> {

	  ApiResult deleteById(String id);

	  Set<String> selectRoleSignByUid(String userId);

	  List<RoleDto> selectAllRoles();
}
