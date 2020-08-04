package com.thinkcms.service.api.tags;
import com.thinkcms.service.dto.tags.CmsTagsDto;
import com.thinkcms.core.api.BaseService;

import java.util.List;

/**
 * <p>
 * 标签 服务类
 * </p>
 *
 * @author LG
 * @since 2019-12-09
 */
public interface CmsTagsService extends BaseService<CmsTagsDto> {


    /**
     * 保存标签
     * @param tags
     * @return
     */
    List<String> saveTags(List<String> tags);

    List<String> getTagsByIds(String tagIds);

    List<CmsTagsDto> getTagsByContentId(String contentId);

    List<CmsTagsDto> listTags(Integer maxRowNum);

    void update(CmsTagsDto v);
}