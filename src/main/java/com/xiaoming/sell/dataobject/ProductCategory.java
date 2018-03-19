package com.xiaoming.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 类目
 * 表: product_category
 */
@Entity //实例类注解，需要spring-boot-starter-data-jpa.jar 支持
@DynamicUpdate //自动更新时间等 需要加此注解
@Data   //自动生成get、set、toString方法，lombok 插件支持
public class ProductCategory {
    public ProductCategory(){

    }

    /** 类目ID **/
    @Id //ID字段
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自动更新ID值
    private Integer categoryId;
    /** 类目名称 **/
    private String categoryName;
    /** 类目编号 **/
    private Integer categoryType;

    private Date createTime;
    private Date updateTime;


    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
