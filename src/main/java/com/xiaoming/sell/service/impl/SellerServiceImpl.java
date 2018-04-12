package com.xiaoming.sell.service.impl;

import com.xiaoming.sell.dao.SellerInfoDao;
import com.xiaoming.sell.dataobject.SellerInfo;
import com.xiaoming.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoDao repository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
