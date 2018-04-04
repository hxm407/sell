package com.xiaoming.sell.pc;


import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Robot {
   // 地址
    private static final String URL = "http://www.ziroom.com/z/nl/z2.html?qwd=&p=5";
//    private static final String URL = "http://www.tooopen.com/view/1439719.html";
    // 获取标签正则
    private static final String BT_REG = "<h3><a target=\"_blank\".*href=(.*?)[^>]*?>*</a>";//标题
    // 获取地区正则
    private static final String DQ_REG = "<h4><a target=\"_blank\".*href=(.*?)[^>]*?>*</a>";//地区
    private static final String XX_REG = "<div class=\"detail\">";//详细信息
    private static final String MK_REG = "<li class=\"clearfix\">";//模块信息

    //    private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";
    // 获取src路径的正则
    private static final String IMGSRC_REG = "[a-zA-z]+://[^\\s]*";

    public static void main(String[] args) {
        try {
            Robot cm=new Robot();
            //获得html文本内容
            String HTML = cm.getHtml(URL);


            getContent(HTML);


            //获取标题信息
//            List<String> btUrl = cm.getImageUrl(HTML,BT_REG);
//            for (String text:btUrl) {
//                System.out.println(text);
//            }
//            List<String> dqUrl = cm.getImageUrl(HTML,DQ_REG);
//            for (String text:dqUrl) {
//                System.out.println(text);
//            }
//            List<String> xxUrl = cm.getImageUrl(HTML,XX_REG);
//            for (String text:xxUrl) {
//                System.out.println(text);
//            }


            //获取图片src地址
//            List<String> imgSrc = cm.getImageSrc(imgUrl);
            //下载图片
//            cm.Download(imgSrc);

        }catch (Exception e){
            System.out.println("发生错误");
        }

    }

    //获取HTML内容
    private String getHtml(String url)throws Exception{
        URL url1=new URL(url);
        URLConnection connection=url1.openConnection();
//        InputStream in=connection.getInputStream();
//        InputStreamReader isr=new InputStreamReader(in);
//        BufferedReader br=new BufferedReader(isr);
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));

        String line;
        StringBuffer sb=new StringBuffer();
        while((line=br.readLine())!=null){
            sb.append(line,0,line.length());
            sb.append('\n');
        }
        br.close();
//        isr.close();
//        in.close();
        return sb.toString();
    }

    //获取ImageUrl地址
    private List<String> getImageUrl(String html,String reg){
        Matcher matcher= Pattern.compile(reg).matcher(html);
        List<String>listimgurl=new ArrayList<String>();
        while (matcher.find()){
            int start = matcher.start();
            System.out.println(start+"==>"+ html.substring(start,html.indexOf("</div>",start)));
            listimgurl.add(matcher.group());
        }
        return listimgurl;
    }

    //获取信息
    private static List<Info> getContent(String html){
        Matcher mk_matcher = Pattern.compile(MK_REG).matcher(html);//模块信息
        Matcher dq_matcher = Pattern.compile(DQ_REG).matcher(html);

        ArrayList<Info> infoList = new ArrayList<>();
        ArrayList<Integer> mksize = new ArrayList<>();
        while (mk_matcher.find()){
//            System.out.println(mk_matcher.start()+" "+mk_matcher.end());
            mksize.add(new Integer(mk_matcher.start()));
//            Info info = new Info();
//            info.setTitle(bt_matcher.group());
//            info.setUrl(bt_matcher.group());
        }
        mksize.add(new Integer(html.indexOf("</li>",mksize.get(mksize.size()-1))));
        for (int i = 0; i < mksize.size()-1; i++) {
            System.out.println("====>"+i);
            System.out.println(html.substring(mksize.get(i),mksize.get(i+1)));
        }
        return  null;
    }

    private static void getHtmlContext(String html){

    }

    //获取ImageSrc地址
    private List<String> getImageSrc(List<String> listimageurl){
        List<String> listImageSrc=new ArrayList<String>();
        for (String image:listimageurl){
            Matcher matcher=Pattern.compile(IMGSRC_REG).matcher(image);
            while (matcher.find()){
                listImageSrc.add(matcher.group().substring(0, matcher.group().length()-1));
            }
        }
        return listImageSrc;
    }

    //下载图片
    private void Download(List<String> listImgSrc) {
        try {
            //开始时间
            Date begindate = new Date();
            for (String url : listImgSrc) {
                //开始时间
                Date begindate2 = new Date();
                String imageName = url.substring(url.lastIndexOf("/") + 1, url.length());
                URL uri = new URL(url);
                InputStream in = uri.openStream();
                FileOutputStream fo = new FileOutputStream(new File("src/res/"+imageName));
                byte[] buf = new byte[1024];
                int length = 0;
                System.out.println("开始下载:" + url);
                while ((length = in.read(buf, 0, buf.length)) != -1) {
                    fo.write(buf, 0, length);
                }
                in.close();
                fo.close();
                System.out.println(imageName + "下载完成");
                //结束时间
                Date overdate2 = new Date();
                double time = overdate2.getTime() - begindate2.getTime();
                System.out.println("耗时：" + time / 1000 + "s");
            }
            Date overdate = new Date();
            double time = overdate.getTime() - begindate.getTime();
            System.out.println("总耗时：" + time / 1000 + "s");
        } catch (Exception e) {
            System.out.println("下载失败");
        }
    }

}
