package com.xiaoming.sell.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xiaoming.sell.dataobject.OrderDetail;
import com.xiaoming.sell.enums.OrderStatusEnum;
import com.xiaoming.sell.enums.PayStatusEnum;
import com.xiaoming.sell.utils.serializer.Date2LongSerializer;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单表——传输层
 */
@Data
@DynamicUpdate
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    /** 订单ID **/
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
    private Integer orderStatus ;
     /** 支付状态，默认0未支付 **/
    private Integer payStatus ;
     /** 创建时间 **/
     @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
     /** 修改时间 **/
     @JsonSerialize(using = Date2LongSerializer.class)
     private Date updateTime;

    /** 订单详情 **/
    private List<OrderDetail> orderDetailList ;

}
