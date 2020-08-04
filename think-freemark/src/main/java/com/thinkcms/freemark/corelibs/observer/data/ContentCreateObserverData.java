package com.thinkcms.freemark.corelibs.observer.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.thinkcms.core.constants.Constants;
import com.thinkcms.core.model.PageKeyWord;
import com.thinkcms.core.utils.Checker;
import com.thinkcms.core.utils.SpringContextHolder;
import com.thinkcms.freemark.corelibs.observer.ObserverAction;
import com.thinkcms.freemark.corelibs.observer.ObserverData;
import com.thinkcms.service.api.category.CmsCategoryService;
import com.thinkcms.service.dto.category.CmsCategoryDto;
import com.thinkcms.service.dto.content.ContentDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * <p>
 * 内容
 * </p>
 *
 * @author LG
 * @since 2019-10-30
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContentCreateObserverData extends ObserverData {

    private CmsCategoryService cmsCategoryService =null;


    public ContentCreateObserverData(ObserverAction action){
        super(action);
    }

    private ContentDto contentDto;

    private CmsCategoryDto categoryDto;

    private String contentId;

    private String tempPath;

    private String categoryCode;

    private String categoryId;

    private  Boolean  hasStatic ;

    private Boolean hasRelated;

    private Boolean hasFiles;

    private Boolean hasTag;

    private String rulesData;


    public ContentCreateObserverData buildParams(){
        if(Checker.BeNotNull(contentDto)){
            if(Checker.BeNull(categoryDto)){
                cmsCategoryService = SpringContextHolder.getBean(CmsCategoryService.class);
                this.categoryDto=cmsCategoryService.getByPk(contentDto.getCategoryId());
            }
            this.contentId=contentDto.getId();
            this.tempPath=contentDto.getTemplatePath();
            this.categoryCode=categoryDto.getCode();
            this.categoryId=categoryDto.getId();
            this.hasStatic=Checker.BeNull(contentDto.getHasStatic())?false:contentDto.getHasStatic();
            //this.hasRelated=Checker.BeNotEmpty(contentDto.getCmsContentRelateds());
            this.hasRelated=Checker.BeNull(contentDto.getHasRelated())?false:contentDto.getHasRelated();
            this.hasTag=Checker.BeNotBlank(contentDto.getTagIds());
            this.hasFiles=contentDto.getHasFiles();
            this.rulesData=contentDto.getRulesData();
            Map<String,Object> mapData=this.getMapData();
            mapData.put(Constants.hasFiles,this.hasFiles);
            mapData.put(Constants.hasTag,this.hasTag);
            mapData.put(Constants.hasRelated,this.hasRelated);
            PageKeyWord pageKeyWord = new PageKeyWord();
            String title = contentDto.getTitle();
            if(Checker.BeNotBlank(categoryDto.getTitle())){
                title=(categoryDto.getTitle()+"-"+title);
            }
            pageKeyWord.setTitle(title).setDescription(categoryDto.getDescription()).setKeywords(categoryDto.getKeywords());
            this.setPageKeyWord(pageKeyWord);
        }
        return this;
    }

}
