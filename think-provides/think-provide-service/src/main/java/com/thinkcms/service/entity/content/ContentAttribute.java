package com.thinkcms.service.entity.content;

import com.thinkcms.core.annotation.FieldDefault;
import com.thinkcms.core.constants.InputTypeEnum;
import com.thinkcms.core.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * <p>
 * 内容扩展
 * </p>
 *
 * @author LG
 * @since 2019-10-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("thinkcms_content_attribute")
public class ContentAttribute extends BaseModel {

private static final long serialVersionUID = 1L;


        @TableField("content_id")
        private String contentId;


    /**
     * 内容来源
     */

        @TableField("source")
        private String source;


    /**
     * 来源地址
     */

        @TableField("source_url")
        private String sourceUrl;


    /**
     * 数据JSON
     */

        @TableField("data")
        private String data;


    /**
     * 全文索引文本
     */

        @TableField("search_text")
        private String searchText;


    /**
     * 内容
     */

        @FieldDefault(fieldNote = "内容",inputType = InputTypeEnum.EDITOR)
        @TableField("text")
        private String text;


    /**
     * 字数
     */

        @TableField("word_count")
        private Integer wordCount;



}
