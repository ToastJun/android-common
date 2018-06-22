package com.toast.core.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
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

        initArouter();
    }

    private void initArouter() {
        if (true) {
            // 打印日志
            ARouter.openLog();
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
        }
        // 尽可能早，推荐在Application中初始化
        ARouter.init(this);
    }
}
