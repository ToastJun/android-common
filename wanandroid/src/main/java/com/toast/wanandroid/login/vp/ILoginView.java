package com.toast.wanandroid.login.vp;

import com.toast.core.base.WIBaseView;

/**
 * Created by wwj on 2018/7/5
 */
public interface ILoginView extends WIBaseView {
    void showLoginLoadingProgress();

    void hideLoginLoadingProgress();

    void setErrorUserName(String tip);

    void hideErrorUserName();

    void setErrorPassword(String tip);

    void
}
