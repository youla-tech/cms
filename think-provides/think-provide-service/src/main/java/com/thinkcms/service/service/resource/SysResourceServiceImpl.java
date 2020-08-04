package com.thinkcms.service.service.resource;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.thinkcms.core.annotation.CacheClear;
import com.thinkcms.core.config.ThinkCmsConfig;
import com.thinkcms.core.constants.Constants;
import com.thinkcms.core.service.BaseServiceImpl;
import com.thinkcms.core.utils.Checker;
import com.thinkcms.service.api.resource.SysResourceService;
import com.thinkcms.service.dto.resource.SysResourceDto;
import com.thinkcms.service.entity.resource.SysResource;
import com.thinkcms.service.mapper.resource.SysResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LG
 * @since 2019-11-11
 */
@Service
public class SysResourceServiceImpl extends BaseServiceImpl<SysResourceDto, SysResource, SysResourceMapper> implements SysResourceService {


    @Autowired
    protected FastFileStorageClient storageClient;

    @Autowired
    ThinkCmsConfig thinkCmsConfig;

    @Cacheable(value = Constants.cacheName, key = "#root.targetClass+'.'+#root.methodName+'.'+#p0", unless = "#result == null")
    @Override
    public String getfilePathById(String id) {
        String path = "";
        if (Checker.BeNotBlank(id)) {
            QueryWrapper<SysResource> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", id).select("file_full_path");
            SysResource sysResource = super.baseMapper.selectOne(queryWrapper);
            if (Checker.BeNotNull(sysResource)) {
                path = sysResource.getFileFullPath();
            }
        }
        return path;
    }


    @CacheClear(keys = {"getfilePathById", "getByPks", "getByPk"})
    @Override
    public void deleteByFilePath(String filePath) {
        Map<String, Object> param = new HashMap<>(16);
        param.put("file_path", filePath);
        baseMapper.deleteByMap(param);
    }
}
