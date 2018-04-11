package com.xiaoming.sell.form;

import lombok.Data;

/**
 * 热销榜 表单
 */
@Data
public class CategoryForm {
    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;
}
