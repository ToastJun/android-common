package com.toast.core.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.toast.core.log.L;

/**
 * Activity基类 所有的应用中的activity都应该继承该类
 * 常用方法
 * dialog
 * rxBus事件容器
 * view和presenter的绑定 解绑
 */
public abstract class WBaseActivity<V extends WIBaseView, T extends WBasePresenter<V>> extends WAbsActivity{

//    private Unbinder

    /**
     * 返回布局文件id
     * @return
     */
    public abstract int getLayoutID();

    /**
     * 控件初始化完成 在这个方法中可以使用
     */
    public abstract void initView();

    public abstract T createPresenter();

    protected Context mBaseActivity;

    protected Context mApplicationContext;

    /**
     * presenter 不能为空
     */
    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            L.e("程序被系统回收,重新启动");
            Intent i = getBaseContext().getPackageManager()
                    .getLaunchIntentForPackage(getBaseContext().getPackageName());
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
            return;
        }
        //
//        ARouter.getInstance().inject(this)

        // 沉浸式statusbar
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(android.R.color.holo_blue_bright));
        }

        beforeSetContentView();
        setContentView(getLayoutID());
//        unbinder = ButterKnife.bind(this);

        // 创建Presenter
        mPresenter = createPresenter();
        // 内存泄漏
        // 关联view
        mPresenter.attachView((V) this);

        mBaseActivity = this;
        mApplicationContext = getApplicationContext();
        initView();

        mPresenter.start();
        L.i("创建Activity===== " + getClass().getSimpleName());
    }

    /**
     * 在 contentview set 之前调用
     */
    public void beforeSetContentView(){}

    @Override
    protected void onDestroy() {
        // 解除 butterknife绑定
//        if (unbinder != null) {
//            unbinder.unbind();
//        }
        // 解除presenter和view的绑定
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        // 解除观察者的订阅关系

        L.i("退出activity===== " + getClass().getSimpleName());
        super.onDestroy();
    }
}
