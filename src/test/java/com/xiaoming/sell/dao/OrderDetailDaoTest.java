package com.xiaoming.sell.dao;

import com.xiaoming.sell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {
    @Autowired
    private OrderDetailDao dao;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1234567890");
        orderDetail.setOrderId("111112");
        orderDetail.setProductId("666");
        orderDetail.setProductIcon("http://xxx.com");
        orderDetail.setProductName("皮蛋");
        orderDetail.setProductPrice(new BigDecimal(3.2));
        orderDetail.setProductQuantity(3);

        OrderDetail result = dao.save(orderDetail);
        Assert.assertNotNull(result);
    }


    @Test
    public void findByOrderId() throws Exception {
       List<OrderDetail> orderDetails =  dao.findByOrderId("111111");
       Assert.assertNotEquals(0,orderDetails.size());
    }

}