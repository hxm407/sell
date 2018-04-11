package com.xiaoming.sell.service;

import com.xiaoming.sell.dataobject.ProductCategory;

import java.util.List;

/**
 * 类目
 */
public interface CategoryService {
   ProductCategory findOne(Integer categoryId);
   ProductCategory getOne(Integer categoryId);

   List<ProductCategory> findAll();

   List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

   ProductCategory save(ProductCategory productCategory);
}
