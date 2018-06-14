package com.toast.summary.mvp.ui.login;

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

import com.toast.summary.MainActivity;
import com.toast.summary.R;
import com.toast.summary.mvp.interactor.login.LoginInteractorImpl;
import com.toast.summary.mvp.presenter.login.LoginPresenter;
import com.toast.summary.mvp.presenter.login.LoginPresenterImpl;
import com.toast.summary.mvp.view.login.LoginView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {

    @Bind(R.id.navbar_back)
    ImageView navbarBack;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.et_pwd)
    EditText etPwd;
    @Bind(R.id.img_pwd_eye)
    ImageView imgPwdEye;
    @Bind(R.id.btn_login)
    TextView btnLogin;
    @Bind(R.id.login_progress)
    ProgressBar loginProgress;
    @Bind(R.id.tv_phone_error)
    TextView tvPhoneError;
    @Bind(R.id.tv_password_error)
    TextView tvPasswordError;

    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mLoginPresenter = new LoginPresenterImpl(this, new LoginInteractorImpl());

        btnLogin.setOnClickListener(this);
        imgPwdEye.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        mLoginPresenter.onDestroy();
        super.onDestroy();
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
        etPwd.setInputType(EditorInfo.TYPE_TEXT_VARIATION_PASSWORD|EditorInfo.TYPE_CLASS_TEXT);
        etPwd.setSelection(etPwd.getText().toString().trim().length());
        imgPwdEye.setImageResource(R.drawable.ic_password_eye_off);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                mLoginPresenter.validateCredentials(etPhone.getText().toString().trim(),
                        etPwd.getText().toString().trim());
                break;

            case R.id.img_pwd_eye:
                if (etPwd.getInputType() == InputType.TYPE_CLASS_TEXT) {
                    setPwdEyeOff();
                } else if(etPwd.getInputType() == (EditorInfo.TYPE_TEXT_VARIATION_PASSWORD|EditorInfo.TYPE_CLASS_TEXT)){
                    setPwdEyeOn();
                }
                break;
        }
    }
}
