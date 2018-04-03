package com.xiaoming.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单详情表
 */
@Entity
@Data
@DynamicUpdate
public class OrderDetail {

    @Id
    @NotFound(action = NotFoundAction.IGNORE)
    private String detailId;    //
    private String orderId;    //
    private String productId;    //
    private String productName;    //	商品名称
    private BigDecimal productPrice;    //	商品价格
    private Integer productQuantity;    //	商品数量
    private String productIcon;    //	商品小图
    private Date createTime;    //	创建时间
    private Date updateTime;    //	修改时间


}
