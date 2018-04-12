package com.xiaoming.sell.dao;

import com.xiaoming.sell.dataobject.SellerInfo;
import com.xiaoming.sell.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoDaoTest {

    @Autowired
    private SellerInfoDao dao;

    @Test
    public void save(){
        SellerInfo sellerInfo = new SellerInfo();

        sellerInfo.setSellerId(KeyUtil.getUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("pwd");
        sellerInfo.setOpenid("abc");

        SellerInfo result = dao.save(sellerInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOpenid(){
        SellerInfo sellerInfo = dao.findByOpenid("abc");
        Assert.assertEquals("abc",sellerInfo.getOpenid());
    }
}