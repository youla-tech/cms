package com.thinkcms.service.api.resource;

import com.thinkcms.core.api.BaseService;
import com.thinkcms.service.dto.resource.SysResourceDto;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LG
 * @since 2019-11-11
 */
public interface SysResourceService extends BaseService<SysResourceDto> {


	String getfilePathById(String id);

    void deleteByFilePath(String filePath);
}