package com.thinkcms.service.mapper.fragment;

import com.thinkcms.service.dto.fragment.FragmentDto;
import com.thinkcms.service.entity.fragment.Fragment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 页面片段数据表 Mapper 接口
 * </p>
 *
 * @author LG
 * @since 2019-11-07
 */
@Mapper
public interface FragmentMapper extends BaseMapper<Fragment> {

    FragmentDto getByPk(@Param("id") String id);

    List<FragmentDto> getFragmentDataByCode(@Param("code") String code);
}
