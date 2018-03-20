package com.xiaoming.sell.dao;

import com.xiaoming.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoDao extends JpaRepository<ProductInfo,String>{


    List<ProductInfo> findByProductStatus(Integer productStatus);
}
