package top.code666.a200plan.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by code666 on 2017/12/5.
 * 工具类
 * 封装一些常用的方法
 */
public class Tools {

    //获取当前的Timestamp类型时间
    public static Timestamp getStampTime(){
        return new Timestamp(new Date().getTime());
    }

    //将yyyy-MM-dd HH:mm:ss格式的时间转换为Timestamp类型时间
    public static Timestamp getStampTime(String times){
        Timestamp timestamp = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateTime = null;
        try {
            dateTime = sdf.parse(times);
            timestamp = new Timestamp(dateTime.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    //获取当前的String类型时间
    public static String getStringTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date curDate = new java.util.Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }

    //将时间戳转换为Date类型时间
    public static Date getStringTime(Timestamp timestamp){
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        long time = timestamp.getTime();
        return new Date(time);
    }
}
