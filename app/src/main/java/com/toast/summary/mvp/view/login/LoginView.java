package com.toast.summary.mvp.view.login;

/**
 * 登录view接口
 */

public interface LoginView {

    public void showLoginLoadingProgress();

    public void hideLoginLoadingProgress();

    public void setErrorUserName(String tip);

    public void hideErrorUserName();

    public void setErrorPassword(String tip);

    public void hideErrorPassword();

    public void navigateToHome();

    public void setPwdEyeOn();

    public void setPwdEyeOff();
}
