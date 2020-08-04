package com.thinkcms.service.api.content;
import com.thinkcms.service.dto.content.CmsContentRelatedDto;
import com.thinkcms.service.dto.content.ContentDto;
import com.thinkcms.core.api.BaseService;

import java.util.List;

/**
 * <p>
 * 内容推荐 服务类
 * </p>
 *
 * @author LG
 * @since 2019-12-12
 */
public interface CmsContentRelatedService extends BaseService<CmsContentRelatedDto> {


    void saveRelated(String id, List<CmsContentRelatedDto> cmsContentRelateds);

    List<ContentDto> getRelatedByContentId(String contentId, int count);
}