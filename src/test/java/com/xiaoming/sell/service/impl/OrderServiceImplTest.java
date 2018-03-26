package com.xiaoming.sell.service.impl;

import com.xiaoming.sell.dataobject.OrderDetail;
import com.xiaoming.sell.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;
    private final String BUYER_OPENID = "110110";
    private final String ORDER_ID = "1521689864788216856";

    @Test
    public void create() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("张三");
        orderDTO.setBuyerAddress("爱学习大厦");
        orderDTO.setBuyerPhone("12345678901");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        //购物车
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("1234");
        o1.setProductQuantity(1);
        orderDetails.add(o1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("3");
        o2.setProductQuantity(2);
        orderDetails.add(o2);

        orderDTO.setOrderDetailList(orderDetails);

        OrderDTO result = orderService.create(orderDTO);
        log.info("[创建订单]result={}",result);
        Assert.assertNotNull(result);
    }

    @Test
    @Transactional
    public void finOne() throws Exception {
       OrderDTO result = orderService.finOne(ORDER_ID);
       log.info("[查询单个订单] result={}",result);
       Assert.assertEquals(ORDER_ID,result.getOrderId());
    }

    @Test
    @Transactional
    public void findList() throws Exception {
        PageRequest request = new PageRequest(0,2);
        Page<OrderDTO> result = orderService.findList(BUYER_OPENID,request);
        log.info("【查询订单列表】 result={}",request);
        Assert.assertNotEquals(0,result.getTotalElements());
    }

    @Test
    public void cancel() throws Exception {
    }

    @Test
    public void finish() throws Exception {
    }

    @Test
    public void paid() throws Exception {
    }

}