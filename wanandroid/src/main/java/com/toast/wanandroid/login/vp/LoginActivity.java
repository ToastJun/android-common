package com.toast.wanandroid.login.vp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.toast.core.base.WBaseActivity;
import com.toast.wanandroid.R;
import com.toast.wanandroid.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends WBaseActivity<ILoginView, LoginPresenter<ILoginView>> implements ILoginView {

    @BindView(R2.id.navbar_back)
    ImageView navbarBack;
    @BindView(R2.id.et_phone)
    EditText etPhone;
    @BindView(R2.id.et_pwd)
    EditText etPwd;
    @BindView(R2.id.img_pwd_eye)
    ImageView imgPwdEye;
    @BindView(R2.id.btn_login)
    TextView btnLogin;
    @BindView(R2.id.login_progress)
    ProgressBar loginProgress;
    @BindView(R2.id.tv_phone_error)
    TextView tvPhoneError;
    @BindView(R2.id.tv_password_error)
    TextView tvPasswordError;


    @Override
    public int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

    }

    @Override
    public LoginPresenter<ILoginView> createPresenter() {
        return new LoginPresenter<>();
    }

    @OnClick({R2.id.btn_login, R2.id.img_pwd_eye})
    public void onClick(View view) {

        if (view.getId() == R.id.btn_login) {
            mPresenter.validateCredentials(etPhone.getText().toString().trim(),
                                            etPwd.getText().toString().trim());
        } else if (view.getId() == R.id.img_pwd_eye) {
            if (etPwd.getInputType() == InputType.TYPE_CLASS_TEXT) {
                setPwdEyeOff();
            } else if (etPwd.getInputType() == (EditorInfo.TYPE_TEXT_VARIATION_PASSWORD | EditorInfo.TYPE_CLASS_TEXT)) {
                setPwdEyeOn();
            }
        }
    }

    @Override
    public void showLoginLoadingProgress() {
        loginProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoginLoadingProgress() {
        loginProgress.setVisibility(View.GONE);
    }

    @Override
    public void setErrorUserName(String tip) {
        tvPhoneError.setText(tip);
        tvPhoneError.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideErrorUserName() {
        tvPhoneError.setVisibility(View.GONE);
    }

    @Override
    public void setErrorPassword(String tip) {
        tvPasswordError.setText(tip);
        tvPasswordError.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideErrorPassword() {
        tvPasswordError.setVisibility(View.GONE);
    }

    @Override
    public void navigateToHome() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void setPwdEyeOn() {
        etPwd.setInputType(EditorInfo.TYPE_CLASS_TEXT);
        etPwd.setSelection(etPwd.getText().toString().trim().length());
        imgPwdEye.setImageResource(R.drawable.ic_password_eye_on);
    }

    @Override
    public void setPwdEyeOff() {
        etPwd.setInputType(EditorInfo.TYPE_TEXT_VARIATION_PASSWORD | EditorInfo.TYPE_CLASS_TEXT);
        etPwd.setSelection(etPwd.getText().toString().trim().length());
        imgPwdEye.setImageResource(R.drawable.ic_password_eye_off);
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
