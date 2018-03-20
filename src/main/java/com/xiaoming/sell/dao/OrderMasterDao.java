package com.xiaoming.sell.dao;

import com.xiaoming.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterDao extends JpaRepository<OrderMaster,String>{
    /** 根据订单ID 号查询 **/
    Page<OrderMaster> findByBuyerOpenid(String buyOpendi, Pageable pageable);
}
