package com.xiaoming.sell;

public class StringTest {
    public static void main(String[] args) {
        //                    45000 元/月
//        info.setJg(priceStr.substring(0,priceStr.indexOf("元")).trim());
//        info.setUnit(priceStr.substring(priceStr.indexOf("元")));
        String priceStr = "45000 元/月";
        System.out.println(priceStr.substring(0,priceStr.indexOf("元")).trim());
        System.out.println(priceStr.substring(priceStr.indexOf("元")));

//            ￥ 2530 (每月)
        priceStr = "￥ 2530 (每月)";
        System.out.println(priceStr.substring(priceStr.indexOf("￥")+1, priceStr.indexOf("(")).trim());
        System.out.println(priceStr.substring(priceStr.indexOf("(")));

//                [银谷美泉] 银谷美泉   朝阳-朝青 姚家园路甲8号
//                [华腾园] 华腾园   朝阳-劲松东 东三环南路54号
        String str = "银谷美泉   朝阳-朝青 姚家园路甲8号";
        if(str.split(" ").length>1){
        System.out.println(str.split(" ")[1]);
        }
        String addStr = str;
        if (addStr != null && !"".equals(addStr) && addStr.split(" ").length > 1) {
            String s = addStr.split(" ")[1];
            System.out.println("[" + addStr.split(" ")[1] + "] " + addStr);
        } else {
            System.out.println(addStr);
        }
    }
}
