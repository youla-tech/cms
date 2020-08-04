package com.thinkcms.service.api.category;
import com.thinkcms.service.dto.category.CmsCategoryExtendDto;
import com.thinkcms.core.api.BaseService;
import com.thinkcms.core.utils.ApiResult;

/**
 * <p>
 * 分类扩展 服务类
 * </p>
 *
 * @author LG
 * @since 2019-11-04
 */
public interface CmsCategoryExtendService extends BaseService<CmsCategoryExtendDto> {


    /**
     * 获取扩展字段展示
     * @param v
     * @return
     */
    ApiResult getExtendFieldById(String id);
}