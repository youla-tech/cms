package com.thinkcms.service.api.model;

import com.thinkcms.service.dto.model.CmsDefaultModelFieldDto;
import com.thinkcms.service.dto.model.CmsModelDto;
import com.thinkcms.core.api.BaseService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LG
 * @since 2019-10-23
 */
public interface CmsModelService extends BaseService<CmsModelDto> {

  /**
   * 获取系统默认字段
   * @return
   */
  List<CmsDefaultModelFieldDto> listDefaultField();


    List<CmsModelDto> listModelByCategory(CmsModelDto v);
}
