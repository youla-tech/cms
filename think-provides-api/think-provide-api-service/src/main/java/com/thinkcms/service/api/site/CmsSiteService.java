package com.thinkcms.service.api.site;
import com.thinkcms.service.dto.site.CmsSiteDto;
import com.thinkcms.core.api.BaseService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LG
 * @since 2019-12-11
 */
public interface CmsSiteService extends BaseService<CmsSiteDto> {


    /**
     * 获取网站配置
     * @return
     */
    CmsSiteDto getSite();

    void updateById(CmsSiteDto v);

    void save(CmsSiteDto v);

    boolean deleteById(String pk);
}