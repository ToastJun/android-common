package com.toast.summary.mvp.presenter.login;

import android.text.TextUtils;

import com.toast.summary.mvp.interactor.login.LoginInteractor;
import com.toast.summary.mvp.view.login.LoginView;

/**
 * Created by Administrator on 2018/6/5.
 */

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnLoginFinishedListener {

    private LoginView loginView;

    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView, LoginInteractor loginInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void validateCredentials(String username, String password) {
        if (loginView != null) {
            loginView.showLoginLoadingProgress();
        }
        // 判断用户名合法性
        if (TextUtils.isEmpty(username)) {
            onUserError("用户名不能为空");
            return;
        }
        // 判断密码是否正确
        if (TextUtils.isEmpty(password)) {
            onPasswordError("密码不能为空");
            return;
        }

        if (loginInteractor != null) {
            loginInteractor.login(username, password, this);
        }
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void onUserError(String tip) {
        loginView.setErrorUserName(tip);
    }

    @Override
    public void onPasswordError(String tip) {
        loginView.setErrorPassword(tip);
    }

    @Override
    public void onSuccess() {
        loginView.navigateToHome();
    }
}
