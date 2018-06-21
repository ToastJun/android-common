package com.toast.core.base;

import com.toast.core.log.L;

import rx.internal.util.SubscriptionList;

public abstract class WBasePresenter<V extends WIBaseView> {

    public V mView;
    public SubscriptionList mSubscriptionList;

    public void attachView(V view) {
        this.mView = view;
        mSubscriptionList = new SubscriptionList();
    }

    public void detachView() {

        if (mView != null) {
            mView = null;
            L.e("view被回收了 " + getClass().getSimpleName());
        }
    }

    /**
     * 返回关联的view
     * @return
     */
    public V getView() {
        return mView;
    }

    protected abstract void start();
}
