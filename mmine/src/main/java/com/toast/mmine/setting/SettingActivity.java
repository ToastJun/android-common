package com.toast.mmine.setting;

import com.toast.core.base.WBaseActivity;

public class SettingActivity extends WBaseActivity<ISettingView, SettingPresenter> implements ISettingView{
    @Override
    public int getLayoutID() {
        return 0;
    }

    @Override
    public void initView() {

    }

    @Override
    public SettingPresenter createPresenter() {
        return null;
    }

    @Override
    public void onLogout() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onProgress(String content) {

    }

    @Override
    public void onLoadData(Object o) {

    }

    @Override
    public void onEmptyData() {

    }

    @Override
    public void onLoadError(String msg) {

    }

    @Override
    public void onNetError(String msg) {

    }
}
