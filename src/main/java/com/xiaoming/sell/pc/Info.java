package com.xiaoming.sell.pc;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Id;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Data
public class Info {
    @Id
    private String url;
    private String title;
    private String wz; //位置  [大兴黄村] 4号线枣园
    private String mj;//面积
    private String lc;//楼层
    private String ts;// 庭室
    /** 距离 **/
    private String jl;//距离
    private String kf;//空房
    private String jg;//价格
    private Integer type;//类型
    private String bz;//备注
//    private String price;//价格
    private String unit;//单位
//    private Date createTime;
//    private Date updateTime;
}
