package com.thinkcms.service.service.category;
import com.thinkcms.service.api.category.CmsCategoryModelService;
import com.thinkcms.service.dto.category.CmsCategoryModelDto;
import com.thinkcms.service.entity.category.CmsCategoryModel;
import com.thinkcms.service.mapper.category.CmsCategoryModelMapper;
import com.thinkcms.core.service.BaseServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 分类-模型 关系表 一个分类可以针对不同的模型投稿（本系统暂时只是一对一后期可扩展） 服务实现类
 * </p>
 *
 * @author LG
 * @since 2019-11-06
 */
@Transactional
@Service
public class CmsCategoryModelServiceImpl extends BaseServiceImpl<CmsCategoryModelDto, CmsCategoryModel, CmsCategoryModelMapper> implements CmsCategoryModelService {



}
