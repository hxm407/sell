package com.xiaoming.sell.service.impl;

import com.xiaoming.sell.dao.ProductInfoDao;
import com.xiaoming.sell.dataobject.ProductInfo;
import com.xiaoming.sell.enums.ProductStatusEnum;
import com.xiaoming.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoDao infoDao;

    @Override
    public ProductInfo getOne(String productId) {
        return infoDao.getOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return infoDao.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return infoDao.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return infoDao.save(productInfo);
    }
}
