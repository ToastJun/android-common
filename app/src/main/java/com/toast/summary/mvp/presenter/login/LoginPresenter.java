package com.toast.summary.mvp.presenter.login;

/**
 * Created by Administrator on 2018/6/5.
 */

public interface LoginPresenter {

    void validateCredentials(String username, String password);

    void onDestroy();

}
