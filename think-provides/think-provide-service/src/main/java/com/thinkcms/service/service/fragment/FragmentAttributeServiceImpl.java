package com.thinkcms.service.service.fragment;
import com.thinkcms.service.api.fragment.FragmentAttributeService;
import com.thinkcms.service.dto.fragment.FragmentAttributeDto;
import com.thinkcms.service.entity.fragment.FragmentAttribute;
import com.thinkcms.service.mapper.fragment.FragmentAttributeMapper;
import com.thinkcms.core.service.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LG
 * @since 2019-11-07
 */
@Transactional
@Service
public class FragmentAttributeServiceImpl extends BaseServiceImpl<FragmentAttributeDto, FragmentAttribute, FragmentAttributeMapper> implements FragmentAttributeService {


    @Override
    public boolean deleteByFragmentId(String pk) {
        Map<String,Object> param = new HashMap<>();
        param.put("fragment_id",pk);
        return removeByMap(param);
    }
}
