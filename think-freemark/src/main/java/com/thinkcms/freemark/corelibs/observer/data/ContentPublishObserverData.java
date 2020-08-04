package com.thinkcms.freemark.corelibs.observer.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.thinkcms.freemark.corelibs.observer.ObserverAction;
import com.thinkcms.freemark.corelibs.observer.ObserverData;
import com.thinkcms.service.dto.category.CmsCategoryDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

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
public class ContentPublishObserverData extends ObserverData {

    public ContentPublishObserverData(ObserverAction observerAction){
        super(observerAction);
    }

    private List<CmsCategoryDto> cmsCategoryDtos;

}
