package com.thinkcms.service.dto.navigation;

import com.thinkcms.core.annotation.DirectMark;
import com.thinkcms.core.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor                 //无参构造
@AllArgsConstructor
public class Navigation  extends BaseModel {

    @DirectMark
    private String name;


    @DirectMark
    private String url;



}
