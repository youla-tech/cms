package com.thinkcms.service.dto.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author LG
 * @since 2019-10-23
 */
@Data
@Accessors(chain = true)
public class CmsDefaultModelFieldDto{

    private static final long serialVersionUID = 1L;

    private String fieldNote;

    private String reFieldNote; //重命名默认等于 fieldNote

    private String fieldName;

    private String fieldType;

    private String inputType;

    private Integer maxlength;//最大长度

    private String extendPrefix="";

    public String defaultValue;

    private Object defaultObjValue;

    private Boolean visibleCheck;//是否允许修改必填字段

    private Boolean visibleSwitch;//是否必选择填字段

    private Boolean initCheck = false;//是否默认选中

    private Boolean initSwitch = false;//是否默认选中
}
