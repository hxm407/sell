package com.xiaoming.sell.service.impl;

import com.xiaoming.sell.dataobject.ProductInfo;
import com.xiaoming.sell.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
    @Autowired
    private ProductServiceImpl service;
    @Test
    @Transactional
    public void getOne() throws Exception {
        ProductInfo productInfo =service.getOne("1234");
        Assert.assertEquals("披萨",productInfo.getProductName());
    }

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> productInfoList = service.findUpAll();
        Assert.assertNotEquals(0,productInfoList.size());
    }
    @Test
    public void findAll() throws Exception {
        PageRequest page = new PageRequest(0,2);
        Page<ProductInfo> productInfoList = service.findAll(page);
        System.out.println("===>"+productInfoList.getTotalElements());
        Assert.assertNotEquals(0,productInfoList.getSize());
    }

    @Test
    public void save() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("5");
        productInfo.setProductName("披萨2");
        productInfo.setProductPrice(new BigDecimal(100.2));
        productInfo.setProductStock(10);
        productInfo.setProductDescription("美味披萨2");
        productInfo.setProductIcon("http://xxx2.com");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setProductType(3);
        ProductInfo info = service.save(productInfo);
        Assert.assertNotNull(info);
    }

}