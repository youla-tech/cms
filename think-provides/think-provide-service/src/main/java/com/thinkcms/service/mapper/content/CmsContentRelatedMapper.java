package com.thinkcms.service.mapper.content;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thinkcms.service.dto.content.ContentDto;
import com.thinkcms.service.entity.content.CmsContentRelated;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 内容推荐 Mapper 接口
 * </p>
 *
 * @author LG
 * @since 2019-12-12
 */
@Mapper
public interface CmsContentRelatedMapper extends BaseMapper<CmsContentRelated> {

    List<ContentDto> getRelatedByContentId(@Param("contentId") String contentId, @Param("count") int count);
}
