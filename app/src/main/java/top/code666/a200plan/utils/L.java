package top.code666.a200plan.utils;

import android.util.Log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * Created by code666 on 2017/12/4.
 */

public class L {
    public static boolean isDebug = true;

    public L(){
        throw new UnsupportedOperationException("对象不能被初始化");
    }

    public static void e(String str){
        if (isDebug) {
            Logger.e(str);
        }
    }

    public static void i(String str){
        if (isDebug) {
            Logger.i(str);
        }
    }

    public static void d(String str){
        if (isDebug) {
            Logger.d(str);
        }
    }

    public static void v(String str){
        if (isDebug) {
            Logger.v(str);
        }
    }

    public static void w(String str){
        if (isDebug) {
            Logger.w(str);
        }
    }

    public static void init(){
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)  //是否选择显示线程信息，默认为true
                .methodCount(2)         //方法数显示多少行，默认2行
                .methodOffset(5)        //隐藏方法内部调用到偏移量，默认5
                .tag("200Plan")   //自定义TAG全部标签，默认PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }
}
