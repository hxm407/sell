package com.xiaoming.sell.dao;

import com.xiaoming.sell.dataobject.ProductInfo;
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
public class ProductInfoDaoTest {
    @Autowired
    ProductInfoDao infoDao;

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1234");
        productInfo.setProductName("披萨");
        productInfo.setProductPrice(new BigDecimal(10.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("美味披萨66676");
        productInfo.setProductIcon("http://xxx.com");
        productInfo.setProductStatus(0);
        productInfo.setProductType(2);

        ProductInfo result =  infoDao.save(productInfo);
        Assert.assertNotNull(result);
    }
    @Test
    public void findByProductStatus() throws Exception {
        List<ProductInfo> infoDaos = infoDao.findByProductStatus(0);
        Assert.assertNotEquals(0,infoDaos.size());
    }

}