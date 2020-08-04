package com.thinkcms.service.api.fragment;
import com.thinkcms.service.dto.fragment.FragmentAttributeDto;
import com.thinkcms.core.api.BaseService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LG
 * @since 2019-11-07
 */
public interface FragmentAttributeService extends BaseService<FragmentAttributeDto> {


    boolean deleteByFragmentId(String pk);
}