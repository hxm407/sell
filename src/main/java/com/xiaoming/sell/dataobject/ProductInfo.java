package com.xiaoming.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品对象
 */
@Entity
@Data
public class ProductInfo {
    @Id
    private String productId;
    /** 商品名称 **/
    private String productName ;
    /** 单价 **/
    private BigDecimal productPrice ;
    /** 库存 **/
    private Integer productStock ;
    /** 描述 **/
    private String productDescription;
    /** 小图 **/
    private String productIcon ;
    /** 类目编号 **/
    private Integer productType;
    /** 商品状态，0正常1下架 **/
    private Integer productStatus;
    /** 创建时间 **/
//    private Date createTime;
    /** 修改时间 **/
//    private Date updateTime;
}
