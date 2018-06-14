package com.toast.summary.mvp.interactor.login;

import android.os.Handler;

/**
 * Created by Administrator on 2018/6/5.
 */

public class LoginInteractorImpl implements LoginInteractor {

    @Override
    public void login(final String username, final String password, final OnLoginFinishedListener listener) {
        // 利用handler延迟 模拟登陆
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!"138".equals(username)) {
                    listener.onUserError("用户名错误");
                    return;
                }
                if (!"123456".equals(password)) {
                    listener.onPasswordError("密码错误");
                    return;
                }
                listener.onSuccess();
            }
        }, 2000);

    }
}
