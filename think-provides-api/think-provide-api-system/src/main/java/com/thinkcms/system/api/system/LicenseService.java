package com.thinkcms.system.api.system;

import com.thinkcms.core.model.LicenseProperties;
import com.thinkcms.core.model.PageDto;
import com.thinkcms.core.utils.ApiResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author dl
 * @since 2018-03-19
 */
public interface LicenseService  {


	PageDto<LicenseProperties> listPage(PageDto<LicenseProperties> roleDto);

	ApiResult importLicense(MultipartFile file);

    ApiResult search();
}
