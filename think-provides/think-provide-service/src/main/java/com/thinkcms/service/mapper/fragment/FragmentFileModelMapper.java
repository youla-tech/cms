package com.thinkcms.service.mapper.fragment;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thinkcms.service.entity.fragment.FragmentFileModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 页面片段文件模型 Mapper 接口
 * </p>
 *
 * @author LG
 * @since 2019-11-07
 */
@Mapper
public interface FragmentFileModelMapper extends BaseMapper<FragmentFileModel> {

    /**
     * 根据code 获取页面片段文件位置
     * @param code
     * @return
     */
    String getFragmentFilePathByCode(@Param("code") String code);
}
