package com.xiaoming.sell.dao;

import com.xiaoming.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer>{
    //根据ID，返回多个结果
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
