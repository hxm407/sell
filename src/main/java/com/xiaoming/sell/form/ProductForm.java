package com.xiaoming.sell.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品详情表单内容
 */
@Data
public class ProductForm {


    private String productId;

    /** 名字. */
    private String productName;

    /** 单价. */
    private BigDecimal productPrice;

    /** 库存. */
    private Integer productStock;

    /** 描述. */
    private String productDescription;

    /** 小图. */
    private String productIcon;

    /** 类目编号. */
    private Integer productType;
//    private Integer categoryType;
}
