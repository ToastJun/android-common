package com.toast.summary.main;

import com.toast.common.utils.ResourceUtils;
import com.toast.core.base.WBasePresenter;
import com.toast.summary.R;
import com.toast.summary.bean.MainAppBean;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter<V extends IMainView> extends WBasePresenter<IMainView>{
    @Override
    protected void start() {
        // 加载数据
        loadMainApps();
    }

    public List<MainAppBean> loadMainApps() {
        ArrayList<MainAppBean> mainAppBeans = new ArrayList<>();

        mainAppBeans.add(new MainAppBean("玩Android", "每日5分钟 浏览最新Android资讯", R.drawable.bg_app_main_nvwang, ""));
        mainAppBeans.add(new MainAppBean("玩 音 乐", "随时随地听 音乐", R.drawable.bg_app_main_nvwang, ""));
        mainAppBeans.add(new MainAppBean("玩 漫 画", "看你所想", R.drawable.bg_app_main_nvwang, ""));
        mainAppBeans.add(new MainAppBean("玩 凑 数", "专业凑数", R.drawable.bg_app_main_nvwang, ""));
        getView().onLoadData(mainAppBeans);
        return mainAppBeans;
    }
}
