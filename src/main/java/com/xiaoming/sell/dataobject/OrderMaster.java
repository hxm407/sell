package com.xiaoming.sell.dataobject;

import com.xiaoming.sell.enums.OrderStatusEnum;
import com.xiaoming.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单表
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {
    /** 订单ID **/
    @Id
    private String orderId;
     /** 买家名字 **/
    private String buyerName;
     /** 买家电话 **/
    private String buyerPhone;
     /** 买家地址 **/
    private String buyerAddress;
     /** 买家微信ID **/
    private String buyerOpenid;
     /** 订单总金额 **/
    private BigDecimal orderAmount;
     /** 订单状态,默认0新下单 **/
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
     /** 支付状态，默认0未支付 **/
    private Integer payStatus = PayStatusEnum.WAIT.getCode();
     /** 创建时间 **/
    private Date createTime;
     /** 修改时间 **/
    private Date updateTime;

    /** 订单详情 **/
//    @Transient //不去数据库查找
//    private List<OrderDetail> orderDetailList;

}
