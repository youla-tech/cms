package com.thinkcms.system.service.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.thinkcms.system.api.system.DictService;
import com.thinkcms.system.dto.system.DictDto;
import com.thinkcms.core.service.BaseServiceImpl;
import com.thinkcms.system.entity.system.Dict;
import com.thinkcms.system.mapper.system.DictMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author LG
 * @since 2019-08-29
 */
@Service
public class DictServiceImpl extends BaseServiceImpl<DictDto, Dict, DictMapper> implements DictService {


    @Override
    public List<DictDto> listType(DictDto v) {
        QueryWrapper<Dict> queryWrapper=new QueryWrapper<>();
        queryWrapper.groupBy("type").select("type");
        return ResultT2D(list(queryWrapper));
    }

	@Override
	public List<DictDto> listByType(String type) {
		  QueryWrapper<Dict> queryWrapper=new QueryWrapper<>();
	        queryWrapper.eq("type", type).notIn("parent_id", 0);
		return ResultT2D(this.baseMapper.selectList(queryWrapper));
	}
}
