package com.xiaoming.sell.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xiaoming.sell.enums.ProductStatusEnum;
import com.xiaoming.sell.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品对象
 */
@Entity
@Data
@DynamicUpdate
public class ProductInfo {

    @Id
    @NotFound(action = NotFoundAction.IGNORE)
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
//    private Integer productStatus;

    /** 创建时间 **/
    private Date createTime;
    /** 修改时间 **/
    private Date updateTime;

    /** 状态, 0正常1下架. */
    private Integer productStatus = ProductStatusEnum.UP.getCode();
    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }
}
