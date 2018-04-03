package com.xiaoming.sell;

import com.xiaoming.sell.pc.Info;
import com.xiaoming.sell.pc.InfoDao;
import com.xiaoming.sell.pc.PcType;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPC {
    private static final String URL = "http://www.ziroom.com/z/nl/z2.html?p=";//默认
    private static final String JG_URL = "http://www.ziroom.com/z/nl/z2-o1.html?p=";//价格
    private static final String MJ_URL = "http://www.ziroom.com/z/nl/z2-o3.html?p=";//面积

    private static final String AJK_MR = "https://bj.zu.anjuke.com/fangyuan/p";//默认 /p50 ;价格 /p5-px7 ；最新 /p5-px3；


    @Autowired
    private InfoDao dao;

    public static void main(String[] args) throws IOException {

    }

    @Test
    public void gethtml() throws IOException {
       /* // 安居客
        ArrayList<Info> ajkList = new ArrayList<>();
        ajkList.addAll(getAjkHmlt(AJK_MR + 1 + "/"));//默认


//        dao.saveAll(ajkList);
        System.out.println(ajkList.size());*/
        Document doc = Jsoup.connect(AJK_MR).get();
        Element body = doc.body();
        Elements elements = body.select("div.zu-itemmod ");
        for (Element node : elements) {
//            Element details = node.select("p.details-item").first();
            Element h3 = node.select("h3 > a").first();
            Element details = node.select("address > a").first();
            Element address2 = node.select("address").first();
            if (details != null) {
                System.out.println(h3.attr("href") + details.text() + "\t == " + address2.text());
            } else {
                System.out.println("=====>" + h3.attr("href") + "\t == " + address2.text());
            }

//            String data = details.text();
//            String[] str = data.split("\\|");
//            System.out.println(str[0] + "\t" + str[1] + "\t" + str[2]);
        }
    }

    /**
     * 获取自如html
     *
     * @param url
     * @return
     * @throws IOException
     */
    private static ArrayList<Info> getZrHmlt(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.select("li.clearfix");
        ArrayList<Info> infoList = new ArrayList<>();
        for (Element node : elements) {
            Info info = new Info();
            info.setType(PcType.ZR.getCode());
            Element h3 = node.select("h3 > a").first();
            info.setUrl(h3.attr("href"));
            info.setTitle(h3.text());
            Element h4 = node.select("h4 > a").first();
            info.setWz(h4.text());
            Elements detail = node.select("div.detail > p > span");
            if (detail.size() == 4) {
                info.setMj(detail.get(0).text());
                info.setLc(detail.get(1).text());
                info.setTs(detail.get(2).text());
                info.setJl(detail.get(3).text());
            }
            Element leave = node.select("p.leave").first();
            if (leave != null) {
                info.setKf(leave.text());
            }
            Element price = node.select("div.priceDetail > p.price").first();
            String priceStr = price.text();
            info.setJg(priceStr.substring(priceStr.indexOf("￥") + 1, priceStr.indexOf("(")).trim());
//            ￥ 2530 (每月)
            info.setUnit(priceStr.substring(priceStr.indexOf("(")));

            infoList.add(info);
        }
        return infoList;
    }

    /**
     * 获取安居客html
     *
     * @param url
     * @return
     * @throws IOException
     */
    private static ArrayList<Info> getAjkHmlt(String url) throws IOException {
        int time = 0;
        for (int i = 0; i < 1000; i++) {
            time = i;
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(sdf1.format(new Date())+"\t ==>"+url);
        Document doc = Jsoup.connect(url).get();
        Element body = doc.body();
        Elements elements = body.select("div.zu-itemmod ");
        ArrayList<Info> infoList = new ArrayList<>();
        for (Element node : elements) {
            Info info = new Info();
            info.setType(PcType.AJK.getCode());
            Element h3 = node.select("h3 > a").first();
            info.setUrl(h3.attr("href"));
            info.setTitle(h3.text());

            Element address = node.select("address > a").first();
            String wz = address != null ? address.text() : "";


            Element address2 = node.select("address").first();
//            System.out.println(address2.split(" ")[1]);
            String addStr = address2.text();
            if (addStr != null && !"".equals(addStr) && addStr.split(" ").length > 1) {
                String s = addStr.split(" ")[1];
                info.setWz("[" + addStr.split(" ")[1] + "] " + addStr);
            } else {
                info.setWz(addStr);
            }

            //3室1厅|17平米|15/25层樊兴兴
            Element details = node.select("p.details-item").first();
            String detailStr = details.text();
            String[] str = detailStr.split("\\|");
            if (str.length == 3) {
                info.setMj(str[1]);
                info.setLc(str[2]);
                info.setTs(str[0]);
            }
            //价格
            Element jg = node.select("div.zu-side").first();
            String priceStr = jg.text();
//                    45000 元/月
            info.setJg(priceStr.substring(0, priceStr.indexOf("元")).trim());
            info.setUnit(priceStr.substring(priceStr.indexOf("元")));
            Element jl = node.select("p.clearfix").first();
            info.setJl(jl.text());
            infoList.add(info);
        }
        return infoList;
    }

    /**
     * 自如——采集并保存
     *
     * @throws IOException
     */
    @Test
    public void saveZR() throws IOException {
        // 自如
        ArrayList<Info> infoArrayList = new ArrayList<>();
        for (int i = 1; i < 51; i++) {
            infoArrayList.addAll(getZrHmlt(URL + i));//默认
            infoArrayList.addAll(getZrHmlt(JG_URL + i));//价格
            infoArrayList.addAll(getZrHmlt(MJ_URL + i));//面积
        }
        dao.save(infoArrayList);
        System.out.println(infoArrayList.size());


    }

    @Test
    public void saveAJK() throws IOException {
        // 安居客
        ArrayList<Info> ajkList = new ArrayList<>();
        for (int i = 1; i < 51; i++) {
            ajkList.addAll(getAjkHmlt(AJK_MR + i + "-x2/"));//默认
            ajkList.addAll(getAjkHmlt(AJK_MR + i + "-px7-x2/"));//价格
            ajkList.addAll(getAjkHmlt(AJK_MR + i + "-px3-x2/"));//面积
        }
        dao.save(ajkList);
        System.out.println(ajkList.size());
    }
}

