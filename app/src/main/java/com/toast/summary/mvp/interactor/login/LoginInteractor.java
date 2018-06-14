package com.toast.summary.mvp.interactor.login;

/**
 * login的数据处理 业务逻辑接口
 */

public interface LoginInteractor {

    interface OnLoginFinishedListener{
        void onUserError(String tip);

        void onPasswordError(String tip);

        void onSuccess();
    }

    void login(String username, String password, OnLoginFinishedListener listener);

}
