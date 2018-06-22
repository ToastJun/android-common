package com.toast.summary.vp.welcome;

import android.text.TextUtils;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.toast.app.wanandroid.data.ARouterUrl;
import com.toast.core.base.WBaseActivity;
import com.toast.summary.R;

import butterknife.BindView;

public class WelcomeActivity extends WBaseActivity<IWelcomeView, WelcomePresenter>
        implements IWelcomeView{

    @BindView(R.id.tv_welcome_content)
    TextView tvWelcomeContent;

    @Override
    public int getLayoutID() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView() {
        tvWelcomeContent.setText("我是一条很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长");
        tvWelcomeContent.setSingleLine(true);
        tvWelcomeContent.setEllipsize(TextUtils.TruncateAt.MARQUEE);
    }

    @Override
    public WelcomePresenter createPresenter() {
        return new WelcomePresenter();
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

    @Override
    public void toMainActivity() {
        ARouter.getInstance().build(ARouterUrl.AR_URL_APP_MAIN).navigation();
    }

    @Override
    public void toSplashActivity() {

    }
}
