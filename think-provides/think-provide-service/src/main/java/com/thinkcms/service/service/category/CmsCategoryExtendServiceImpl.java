package com.thinkcms.service.service.category;

import com.thinkcms.service.api.category.CmsCategoryAttributeService;
import com.thinkcms.service.api.category.CmsCategoryExtendService;
import com.thinkcms.service.api.category.CmsCategoryService;
import com.thinkcms.service.dto.category.CmsCategoryDto;
import com.thinkcms.service.dto.category.CmsCategoryExtendDto;
import com.thinkcms.service.dto.model.CmsDefaultModelFieldDto;
import com.thinkcms.service.entity.category.CmsCategoryExtend;
import com.thinkcms.service.mapper.category.CmsCategoryExtendMapper;
import com.thinkcms.service.utils.DynamicFieldUtil;
import com.thinkcms.core.service.BaseServiceImpl;
import com.thinkcms.core.utils.ApiResult;
import com.thinkcms.core.utils.Checker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 分类扩展 服务实现类
 * </p>
 *
 * @author LG
 * @since 2019-11-04
 */
@Service
public class CmsCategoryExtendServiceImpl extends BaseServiceImpl<CmsCategoryExtendDto, CmsCategoryExtend, CmsCategoryExtendMapper> implements CmsCategoryExtendService {

    @Autowired
    CmsCategoryService cmsCategoryService;

    @Autowired
    CmsCategoryAttributeService attributeService;

    @Transactional
    @Override
    public boolean deleteByPk(String id) {
        List<CmsCategoryDto> cmsCategoryDtos=cmsCategoryService.listByField("category_extend_id",id);
        if(Checker.BeNotEmpty(cmsCategoryDtos)){
            cmsCategoryDtos.forEach(cmsCategoryDto->{
                cmsCategoryDto.setCategoryExtendId("");
            });
            cmsCategoryService.updateByPks(cmsCategoryDtos);
        }
        return super.removeById(id);
    }

    @Override
    public ApiResult getExtendFieldById(String id) {
         ApiResult apiResult = ApiResult.result();
         CmsCategoryExtendDto categoryExtend = getByPk(id);
         List<CmsDefaultModelFieldDto> allFields= DynamicFieldUtil.formaterField(null,categoryExtend.getExtendFieldList());
         apiResult.put("extendField",allFields);
         return apiResult;
    }
}
