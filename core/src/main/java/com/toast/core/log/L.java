package com.toast.core.log;


import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * Created by wwj on 2018/6/15
 * 项目的Log日志类
 */
public class L {
    private static boolean isDebug = true;

    public static void init(boolean debug) {
        // 自定义输出格式
        PrettyFormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(0)
                .methodCount(7)
                .tag("My custom tag")
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        isDebug = debug;
    }

    public static void v(String msg) {
        if (isDebug) {
            Logger.v(msg);
        }
    }

    public static void i(String msg){
        if (isDebug) {
            Logger.i(msg);
        }
    }

    public static void d(String msg) {
        if (isDebug) {
            Logger.d(msg);
        }
    }

    public static void w(String msg) {
        if (isDebug) {
            Logger.w(msg);
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            Logger.e(msg);
        }
    }

    public static void wtf(String msg) {
        if (isDebug) {
            Logger.wtf(msg);
        }
    }

    public static void json(String json) {
        if (isDebug) {
            Logger.json(json);
        }
    }
}
