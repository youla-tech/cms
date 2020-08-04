package com.thinkcms.system.service.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.thinkcms.core.enumerate.OrgRoot;
import com.thinkcms.core.handler.CustomException;
import com.thinkcms.core.service.BaseServiceImpl;
import com.thinkcms.core.utils.*;
import com.thinkcms.system.api.system.OrgService;
import com.thinkcms.system.api.system.UserRoleService;
import com.thinkcms.system.api.system.UserService;
import com.thinkcms.system.dto.system.OrgDto;
import com.thinkcms.system.dto.system.UserDto;
import com.thinkcms.system.entity.system.Org;
import com.thinkcms.system.mapper.system.OrgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrgServiceImpl extends BaseServiceImpl<OrgDto, Org, com.thinkcms.system.mapper.system.OrgMapper> implements OrgService {
	
	@Autowired
	private UserService userService;
	
	@Resource
	private OrgMapper OrgMapper;
	
	@Resource
	private UserRoleService userRoleService;


	@Override
	public Tree<OrgDto> selectTreeList() {
		Map<String, Object> map=new HashMap<>(16);
		List<OrgDto> orgs=super.listDto(new OrgDto());
		if(orgs!=null&&!orgs.isEmpty()){
			List<Tree<OrgDto>> trees = new ArrayList<Tree<OrgDto>>();
			for (OrgDto orgDto : orgs) {
				Tree<OrgDto> tree = new Tree<OrgDto>();
				tree.setId(orgDto.getId());
				tree.setKey(orgDto.getId());
				tree.setValue(orgDto.getId());
				tree.setParentId(orgDto.getParentId());
				tree.setName(orgDto.getOrgName());
				tree.setTitle(orgDto.getOrgName());
				Map<String, Object> attributes = new HashMap<>(16);
				attributes.put("orgCode", orgDto.getOrgCode());
				attributes.put("parentCode", orgDto.getParentCode());
				attributes.put("createTime", orgDto.getCreateTime());
				attributes.put("level",orgDto.getLevel());
				tree.setAttributes(attributes);
				trees.add(tree);
			}
			Tree<OrgDto> t = BuildTree.build(trees);
			return t;
		}else{
			return null;
		}
	}

	@Override
	public void saveOrg(OrgDto orgDto) {
		orgDto.setId(generateId());
		OrgDto parentOrg=getByPk(orgDto.getParentId());
		if(Checker.BeNotNull(parentOrg)){
			orgDto.setParentCode(parentOrg.getOrgCode());
		}
		insert(orgDto);
	}

	@Override
	public boolean deleteOrg(String id) {
	    OrgDto orgDto=getByPk(id);
	    if(Checker.BeNotNull(orgDto) && OrgRoot.DEFAULT.getCode().equals(orgDto.getParentId())){
            throw new CustomException(ApiResult.result(5003));
        }
        List<String> ids=new ArrayList<>();
        List<String> total=new ArrayList<>();
        ids.add(id);
        total.add(id);
        getOrgIds(ids,total);
        if(Checker.BeNotEmpty(total)){
            for(String oid: total){
                List<UserDto> userDtos=userService.getUserByOrgId(oid);
                if(Checker.BeNotEmpty(userDtos)){
                    throw new CustomException(ApiResult.result(5004));
                }
            }
           deleteByPks(total);
        }
        return true;
	}

	private List<String> getOrgIds(List<String>ids,List<String> total){
	    if(Checker.BeNotEmpty(ids)){
	        for(String id:ids){
                OrgDto orgDto=new OrgDto();
				orgDto.setParentId(id);
                List<OrgDto> list=listDto(orgDto);
                if(Checker.BeNotEmpty(list)){
                   List<String> newlist=new ArrayList<>();
                   for(OrgDto org:list){
                       if(!newlist.contains(org.getId()))
                       newlist.add(org.getId());
                   }
                   total.addAll(newlist);
                   getOrgIds(newlist,total);
                }
            }
        }
        return total;
    }

    @Override
    public OrgDto info(String id) {
		OrgDto childOrgDto=getByPk(id);
		OrgDto parentOrgDto=getByPk(childOrgDto.getParentId());
        if(Checker.BeNotNull(parentOrgDto)){
            childOrgDto.setParentName(parentOrgDto.getOrgName());
        }else{
            childOrgDto.setParentName(SystemConstant.ROOT_ORG_NAME);
        }
        return childOrgDto;
    }

	@Override
	public void deleteByOrgCode(String code) {
		baseMapper.deleteByOrgCode(code);
	}

	@Override
	public List<OrgDto> listByParentId(String parentId) {
		QueryWrapper<Org> wrapper = new QueryWrapper<>();
		wrapper.eq(Checker.BeNotNull(parentId), "parent_id", parentId);
		List<Org> orgList = OrgMapper.selectList(wrapper);
		return Checker.BeNotNull(orgList) ? T2DList(orgList) : Lists.newArrayList();
	}


	@Override
	public List<OrgDto> getOrgsByUserId(String userId, String orgCode) {
		UserDto userDto=userService.getByPk(userId);
		String goalOrgCode=null;//userDto.getOrgCode().split("-")[0]+orgCode;
		return baseMapper.getOrgsByUserId(goalOrgCode);
	}



}
