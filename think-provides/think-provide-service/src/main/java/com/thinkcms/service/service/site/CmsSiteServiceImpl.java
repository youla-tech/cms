package com.thinkcms.service.service.site;
import com.thinkcms.service.api.site.CmsSiteService;
import com.thinkcms.service.dto.site.CmsSiteDto;
import com.thinkcms.service.entity.site.CmsSite;
import com.thinkcms.service.mapper.site.CmsSiteMapper;
import com.thinkcms.core.annotation.CacheClear;
import com.thinkcms.core.constants.Constants;
import com.thinkcms.core.service.BaseServiceImpl;
import com.thinkcms.core.utils.Checker;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LG
 * @since 2019-12-11
 */
@Transactional
@Service
public class CmsSiteServiceImpl extends BaseServiceImpl<CmsSiteDto, CmsSite, CmsSiteMapper> implements CmsSiteService {



    @Cacheable(value= Constants.cacheName, key="#root.targetClass+'.'+#root.methodName",unless="#result == null")
    @Override
    public CmsSiteDto getSite() {
        List<CmsSite> cmsSites=list();
        if(Checker.BeNotEmpty(cmsSites)){
            return T2D(cmsSites.get(0));
        }
        return null;
    }


    @CacheClear(keys = {"getSite","getByPk"})
    @Override
    public void updateById(CmsSiteDto v) {
        super.updateByPk(v);
    }

    @CacheClear(keys = {"getSite","getByPk"})
    @Override
    public void save(CmsSiteDto v) {
        super.insert(v);
    }

    @CacheClear(keys = {"getSite","getByPk"})
    @Override
    public boolean deleteById(String pk) {
        return super.deleteByPk(pk);
    }
}
