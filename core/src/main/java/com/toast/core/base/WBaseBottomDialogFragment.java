package com.toast.core.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toast.core.R;
import com.toast.core.log.L;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.internal.util.SubscriptionList;

public abstract class WBaseBottomDialogFragment<V extends WIBaseView, T extends WBasePresenter<V>> extends DialogFragment {

    /**
     * RxBus事件订阅者容器
     * 所有的事件订阅者都应该放在这个容器当中
     * 已保证不会发生泄漏
     */

    protected SubscriptionList subscriptions;

    public T mPresenter;

    private Context mActivityContext;

    private Unbinder bind;

    protected abstract int getLayoutId();

    protected abstract T createPresenter();

    protected abstract void initView();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_Design_BottomSheetDialog);
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);

        subscriptions = new SubscriptionList();
        mActivityContext = getContext();
        L.e("fragment 创建 " + getClass().getSimpleName());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), null);
        bind = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        mPresenter.start();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null) {
            this.dismiss();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (bind != null) {
            bind.unbind();
        }
    }
}
