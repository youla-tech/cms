package com.thinkcms.service.service.content;

import com.thinkcms.service.api.content.CmsContentRelatedService;
import com.thinkcms.service.dto.content.CmsContentRelatedDto;
import com.thinkcms.service.dto.content.ContentDto;
import com.thinkcms.service.entity.content.CmsContentRelated;
import com.thinkcms.service.mapper.content.CmsContentRelatedMapper;
import com.thinkcms.core.annotation.CacheClear;
import com.thinkcms.core.config.ThinkCmsConfig;
import com.thinkcms.core.constants.Constants;
import com.thinkcms.core.handler.CustomException;
import com.thinkcms.core.service.BaseServiceImpl;
import com.thinkcms.core.utils.ApiResult;
import com.thinkcms.core.utils.Checker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 内容推荐 服务实现类
 * </p>
 *
 * @author LG
 * @since 2019-12-12
 */
@Transactional
@Service
public class CmsContentRelatedServiceImpl extends BaseServiceImpl<CmsContentRelatedDto, CmsContentRelated,CmsContentRelatedMapper> implements CmsContentRelatedService {


    @Autowired
    ThinkCmsConfig thinkCmsConfig;


    @CacheClear(keys = {"getRelatedByContentId"})
    @Transactional
    @Override
    public void saveRelated(String id, List<CmsContentRelatedDto> cmsContentRelateds) {
        if(Checker.BeNotBlank(id)){
            if(cmsContentRelateds.size()>50){
                throw  new CustomException(ApiResult.result(20019));
            }
            if(Checker.BeNotEmpty(cmsContentRelateds)){
                cmsContentRelateds.forEach(cmsContentRelated->{
                    cmsContentRelated.setContentId(id).setRelatedContentId(cmsContentRelated.getId()).setUserId(getUserId())
                            .setId(generateId());
                });
                deleteByFiled("content_id",id);
                insertBatch(cmsContentRelateds);
            }else{
                deleteByFiled("content_id",id);
            }
        }
    }

    @Cacheable(value= Constants.cacheName, key="#root.targetClass+'.'+#root.methodName+'.'+#p0+'.'+#p1",unless="#result == null")
    @Override
    public List<ContentDto> getRelatedByContentId(String contentId, int count) {
        count= count>50?50:count;
        return baseMapper.getRelatedByContentId(contentId,count);
    }
}
