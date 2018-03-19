package com.xiaoming.sell.service.impl;

import com.xiaoming.sell.dao.ProductCategoryDao;
import com.xiaoming.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServicImplTest {
    @Autowired
    private CategoryServicImpl categoryServic;

    @Test
//    @Transactional
    public void findOne() throws Exception {
         ProductCategory category =  categoryServic.getOne(2);
        System.out.println("==>"+category.toString());
        Assert.assertEquals(new Integer(7),category.getCategoryType());
    }

    @Test
    public void findAll() throws Exception {
        List<ProductCategory> categoryList = categoryServic.findAll();
        Assert.assertNotEquals(0,categoryList.size());
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<ProductCategory> categoryList = categoryServic.findByCategoryTypeIn(Arrays.asList(2,7));
        Assert.assertNotEquals(0,categoryList.size());
    }

    @Test
    public void save() throws Exception {
        ProductCategory productCategory = new ProductCategory("另类",10);
        ProductCategory result = categoryServic.save(productCategory);
        Assert.assertNotEquals(null,result);

    }

}