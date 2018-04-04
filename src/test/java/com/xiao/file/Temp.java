package com.xiao.file;

import javax.xml.crypto.Data;
import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Temp {
    public static String TMP_PATH = "C:\\Users\\admin\\Desktop\\temp";
    public static String DEL_PATH = "C:\\Users\\admin\\Desktop\\delete";
    public static Integer TMP_NUM = new Integer(-2);    //临时目录  时间
    public static Integer DEL_NUM = new Integer(-30);    //删除目录  时间
    public static long TMP_TIME = 0;  //格式化后的时间
    public static long DEL_TIME = 0;  //格式化后的时间
    public static String NEW_TIME = "";  //时间
    public static String TIME_FORMAT = "yyyy-MM-dd";

    static {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //获移动取时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, TMP_NUM);
        TMP_TIME = calendar.getTimeInMillis();
//        TMP_STR = df.format(calendar.getTime());
        //获取删除时间
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, DEL_NUM);
        DEL_TIME = calendar.getTimeInMillis();
//        DEL_STR = df.format(calendar.getTime());
        //时间
        NEW_TIME = df.format(new Date());
    }

    public static void main(String[] args) throws IOException {
        move();
//        delete();

    }

    //      1.临时文件夹中文件，超过23天未更新的移动到delete中
    public static void move() throws IOException {
        HashMap<String, Long> fileMap = lastFileTime(TMP_PATH);
        StringBuffer sb = new StringBuffer();
        sb.append("=============="+NEW_TIME+"==================\r\n");
        for (Map.Entry<String, Long> entry : fileMap.entrySet()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            System.out.println("TMP:"+sdf.format(TMP_TIME)+"\nVAL:"+sdf.format(entry.getValue()));
            if (TMP_TIME > entry.getValue()) {
                Path oldPath = Paths.get(TMP_PATH + "\\" + entry.getKey());
                Path newPath = Paths.get(DEL_PATH + "\\" + entry.getKey());
                Files.move(oldPath, newPath);
                System.out.println("【移动文件】" + entry.getKey());
                sb.append("【移动文件】" + entry.getKey());
            }
        }
        write(TMP_PATH + "/tmp.log", sb.toString());
    }

    //      2.删除文件夹中文件，超过30天未更新将被移动到垃圾箱中
    public static void delete() throws IOException {
        HashMap<String, Long> fileMap = lastFileTime(DEL_PATH);
        StringBuffer sb = new StringBuffer();
        sb.append("=============="+NEW_TIME+"==================\r\n");
        for (Map.Entry<String, Long> entry : fileMap.entrySet()) {
            if (DEL_TIME > entry.getValue()) {
                Path newPath = Paths.get(DEL_PATH + "\\" + entry.getKey());
                Files.delete(newPath);
                System.out.println("【删除文件】" + entry.getKey());
                sb.append("【删除文件】" + entry.getKey()+"\n\r");
            }
        }
        write(DEL_PATH + "/dep.log",sb.toString() );
    }

    public static void write(String path, String content) {
        Path paths = Paths.get(path);
        BufferedWriter writer = null;
        BufferedReader reader = null;
        try {
            File f = new File(path);
            if(!f.exists()){
                f.createNewFile();
            }
            writer = Files.newBufferedWriter(paths, StandardOpenOption.APPEND);
            writer.write(content);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取文件最后修改时间
     *
     * @param path 路径
     * @return 返回   文件名称：最后修改时间
     */
    public static HashMap<String, Long> lastFileTime(String path) {
        HashMap<String, Long> fileMap = new HashMap<>();
        File file = new File(path);
        if (!file.isDirectory()) {
            return null;
        }
        String[] files = file.list();
        for (String fileName : files) {
//            File f = new File(path+"/"+fileName);
//            System.out.println(fileName+" ==> " + longToTime(f.lastModified(),TIME_FORMAT));
//            String filePath = path + "/" + fileName;
//            fileMap.put(fileName, longToTime(new File(path + "/" + fileName).lastModified(), TIME_FORMAT));
            fileMap.put(fileName, new File(path + "/" + fileName).lastModified());
        }
        return fileMap;
    }
}
