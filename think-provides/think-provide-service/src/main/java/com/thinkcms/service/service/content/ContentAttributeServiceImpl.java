package com.thinkcms.service.service.content;
import com.thinkcms.service.entity.content.ContentAttribute;
import com.thinkcms.core.service.BaseServiceImpl;
import com.thinkcms.service.api.content.ContentAttributeService;
import com.thinkcms.service.dto.content.ContentAttributeDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.thinkcms.service.mapper.content.ContentAttributeMapper;

/**
 * <p>
 * 内容扩展 服务实现类
 * </p>
 *
 * @author LG
 * @since 2019-10-30
 */
@Transactional
@Service
public class ContentAttributeServiceImpl extends BaseServiceImpl<ContentAttributeDto, ContentAttribute,ContentAttributeMapper> implements ContentAttributeService {



}
