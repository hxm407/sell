package com.xiaoming.sell.service.impl;

import com.xiaoming.sell.converter.OrderMaster2OrderDTOConverter;
import com.xiaoming.sell.dao.OrderDetailDao;
import com.xiaoming.sell.dao.OrderMasterDao;
import com.xiaoming.sell.dataobject.OrderDetail;
import com.xiaoming.sell.dataobject.OrderMaster;
import com.xiaoming.sell.dataobject.ProductInfo;
import com.xiaoming.sell.dto.CartDTO;
import com.xiaoming.sell.dto.OrderDTO;
import com.xiaoming.sell.enums.OrderStatusEnum;
import com.xiaoming.sell.enums.PayStatusEnum;
import com.xiaoming.sell.enums.ResultEnum;
import com.xiaoming.sell.exception.SellException;
import com.xiaoming.sell.service.OrderService;
import com.xiaoming.sell.service.ProductService;
import com.xiaoming.sell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
    private OrderMasterDao orderMasterDao;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        String orderId = KeyUtil.getUniqueKey();
        List<CartDTO> cartDTOList = new ArrayList<>();

        //1. 查询商品（数量、价格）
        for(OrderDetail orderDetail: orderDTO.getOrderDetailList()){
            ProductInfo productInfo = productService.getOne(orderDetail.getProductId());
            if(productInfo == null ){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2. 计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //订单详情入库
            BeanUtils.copyProperties(productInfo,orderDetail);//拷贝productInfo 给orderDetail
            //注意：拷贝时null也会拷贝进去，要先进行拷贝，在进行调整
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            orderDetailDao.save(orderDetail);
//            购物车
//            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
//            cartDTOList.add(CartDTO);
        }

        //3. 写入订单数据库 （orderMaster ， orderDetail）
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterDao.save(orderMaster);

        //4. 扣库存
        cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(),e.getProductQuantity())
            ).collect(Collectors.toList());
        productService.increaseStock(cartDTOList);
        return orderDTO;
    }

    @Override
    public OrderDTO finOne(String oderId) {
        OrderMaster orderMaster = orderMasterDao.getOne(oderId);
        if(orderMaster == null ){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        List<OrderDetail> orderDetails = orderDetailDao.findByOrderId(oderId);
        if(orderDetails == null){
         throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage =  orderMasterDao.findByBuyerOpenid(buyerOpenid,pageable);

        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());

        return orderDTOPage;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
