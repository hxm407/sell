package com.xiaoming.sell.dao;

import com.xiaoming.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {
    @Autowired
    private ProductCategoryDao repository;

    @Test
    public void findOne(){
        ProductCategory category = new ProductCategory();
        category.setCategoryId(1);
        Example<ProductCategory> temp = Example.of(category);
        Optional<ProductCategory> category2 =  repository.findOne(temp);
        System.out.println(category2.get().toString());
    }

    @Test
    @Transactional //事物——完成后回滚
    public void saveTest(){
        //保存
//        ProductCategory productCategory = new ProductCategory();
//        productCategory.setCategoryName("男生排行榜");
//        productCategory.setCategoryType(5);
//        productCategory = repository.save(productCategory);
//        System.out.println("==>"+productCategory.toString());

        //更新
//        ProductCategory temp = new ProductCategory();
//        temp.setCategoryId(2);
//        Example<ProductCategory> example = Example.of(temp);
//        Optional<ProductCategory> result =  repository.findOne(example);
//        ProductCategory p = result.get();
//        p.setCategoryType(7);
//        p = repository.save(p);
//        System.out.println("===>"+p.toString());
        ProductCategory productCategory = new ProductCategory("女生",11);
        ProductCategory result = repository.save(productCategory);
        Assert.assertNotNull(result);
    }
    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(2,7,4,11);
        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,result.size());
    }
}