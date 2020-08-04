package com.thinkcms.service.mapper.tags;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.thinkcms.service.dto.tags.CmsTagsTypeDto;
import com.thinkcms.service.entity.tags.CmsTagsType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 标签类型 Mapper 接口
 * </p>
 *
 * @author LG
 * @since 2020-01-31
 */
@Mapper
public interface CmsTagsTypeMapper extends BaseMapper<CmsTagsType> {

    IPage<CmsTagsTypeDto> listPage(IPage<CmsTagsTypeDto> pages, @Param("dto") CmsTagsTypeDto dto);
}
