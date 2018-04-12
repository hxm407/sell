package com.xiaoming.sell.service.impl;

import com.xiaoming.sell.dto.OrderDTO;
import com.xiaoming.sell.service.OrderService;
import com.xiaoming.sell.service.PushMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class PushMessageServiceImplTest {
    @Autowired
    private PushMessageServiceImpl pushMessageService;

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void orderStatus() throws Exception {
        OrderDTO orderDTO = orderService.finOne("1523345755880571705");
        pushMessageService.orderStatus(orderDTO);
    }

}