package com.thinkcms.web.controller.webapi;

import com.thinkcms.service.api.content.ContentService;
import com.thinkcms.service.dto.content.ContentDto;
import com.thinkcms.service.dto.tags.CmsTagsDto;
import com.thinkcms.core.handler.CustomException;
import com.thinkcms.core.model.PageDto;
import com.thinkcms.core.model.SolrSearchModel;
import com.thinkcms.core.utils.ApiResult;
import com.thinkcms.core.utils.Checker;
import com.thinkcms.core.utils.HttpServletUtils;
import com.thinkcms.web.controller.BaseController;
import org.apache.solr.common.SolrDocument;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 内容 前端控制器
 * </p>
 *
 * @author LG
 * @since 2019-10-30
 */
@Validated
@RestController
@RequestMapping("/api/search")
public class SearchController extends BaseController<ContentService> {

    /**
     * 关键字搜索
     * @param pageDto
     * @return
     */
    @PostMapping("searchKeyWord")
    public PageDto<SolrDocument> searchKeyWord(@RequestBody PageDto<SolrSearchModel> pageDto){
        if(Checker.BeNull(pageDto) || Checker.BeNull(pageDto.getDto())|| pageDto.getPageSize()>100){
            throw  new CustomException(ApiResult.result(20018));
        }
        return service.searchKeyWord(pageDto);
    }



    /**
     * 标签搜索搜索
     * @param pageDto
     * @return
     */
    @PostMapping("searchByTag")
    public PageDto<ContentDto> searchByTag(@RequestBody PageDto<CmsTagsDto> pageDto){
        if(Checker.BeNull(pageDto) || Checker.BeNull(pageDto.getDto())|| pageDto.getPageSize()>100){
            throw  new CustomException(ApiResult.result(20018));
        }
        return service.searchByTag(pageDto);
    }

    /**
     * 获取文章的点击次数
     * @param contentId
     * @return
     */
    @GetMapping("searchClicks")
    public Long searchClicks(@RequestParam String contentId){
        return service.searchClicks(contentId);
    }


    /**
     * 获取文章的点赞次数
     * @param contentId
     * @return
     */
    @GetMapping("searchGiveLikes")
    public ApiResult searchGiveLikes(HttpServletRequest request, @RequestParam String contentId,@RequestParam Boolean isClick){
        String userAgent=HttpServletUtils.getIpAddress(request);
        return service.searchGiveLikes(contentId,userAgent,isClick);
    }


}
