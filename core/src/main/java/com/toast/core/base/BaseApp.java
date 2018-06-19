package com.toast.core.base;

import android.app.Application;

import com.toast.common.utils.Utils;

/**
 * Created by wwj on 2018/6/15
 */
public class BaseApp extends Application {

    private static BaseApp sMyApplication = null;

    public static BaseApp getApplication (){
        return sMyApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sMyApplication = this;
        // Utils类的初始化
        Utils.init(this);
    }
}
