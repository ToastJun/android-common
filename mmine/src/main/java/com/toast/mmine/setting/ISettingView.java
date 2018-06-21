package com.toast.mmine.setting;

import com.toast.core.base.WIBaseView;

/**
 * 设置界面的view操作
 */
public interface ISettingView extends WIBaseView {
    void onLogout();
    void showProgress();
    void onProgress(String content);
}
