package com.xiaoming.sell.controller;

import com.xiaoming.sell.VO.ProductInfoVO;
import com.xiaoming.sell.VO.ProductVO;
import com.xiaoming.sell.VO.ResultVO;
import com.xiaoming.sell.dataobject.ProductCategory;
import com.xiaoming.sell.dataobject.ProductInfo;
import com.xiaoming.sell.service.CategoryService;
import com.xiaoming.sell.service.ProductService;
import com.xiaoming.sell.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 */
@RestController  ///返回json格式
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("list")
    public ResultVO list(){
        //1.查询所有的上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //2.查询类目（一次性查询）
        //传统方法
//        List<Integer> categoryTypeList = new ArrayList<>();
//        for (ProductInfo productInfo:productInfoList) {
//            categoryTypeList.add(productInfo.getProductType());
//        }
        //精简方法（java8,lambda)
        List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getProductType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //3.数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory: productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo:productInfoList){
                if(productInfo.getProductType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);//拷贝productInfo的值到productInfoVO中
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

//        ResultVO resultVO = new ResultVO();
//        resultVO.setCode(0);
//        resultVO.setMsg("成功");
//        resultVO.setData(productVOList);


        return  ResultVOUtil.success(productVOList);
    }

}
