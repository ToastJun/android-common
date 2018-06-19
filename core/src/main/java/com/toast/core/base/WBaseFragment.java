package com.toast.core.base;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.toast.common.utils.KeyboardUtils;
import com.toast.core.log.L;

import java.security.Key;
import java.util.List;

public abstract class WBaseFragment<V extends WIBaseView, T extends WBasePresenter<V>> extends Fragment {

    private View rootView;

    private boolean mIsShow = false;

    public abstract int getLayoutID();

    /**
     * view初始化完成 可以开始使用控件view
     */
    public abstract void initView();

    protected abstract T createPresenter();

    public T mPresenter;

    private InputMethodManager imm;

    protected Context mActivityContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 创建Presenter
        mPresenter = createPresenter();
        // 内存泄漏 关联view
        mPresenter.attachView((V) this);
//        mActivityContext = getContext();

        L.i("创建Fragment=====" + getClass().getSimpleName());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutID(), null);

//        bind = ButterKnife.bind(this, rootView)
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        mPresenter.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        bind.unbind();
        // 解绑view
        mPresenter.detachView();
        // 解除订阅关系

        L.i("销毁fragment: " + getClass().getSimpleName());
    }

    public Context getActivityContext(){return mActivityContext;}

    public boolean isShow(){
        return mIsShow;
    }

    public void setShow(boolean show) {
        mIsShow = show;
    }

    /**
     *
     * @param views 当前fragment当中 所有能触发软键盘的view的集合, 比如两个EditText
     */
    protected void hideKeyBoard(List<View> views) {
        KeyboardUtils.hideSoftKeyboard(mActivityContext, views);
    }

    protected void showKeyBoard(View focus) {
        if (mActivityContext != null && mActivityContext instanceof Activity) {
            if (imm == null) {

            }
        }
    }
}
