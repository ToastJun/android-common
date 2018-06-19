package com.toast.core.base;

import com.toast.core.log.L;

public abstract class WBasePresenter<V extends WIBaseView> {

    public V mView;

    public void attachView(V view) {
        this.mView = view;
        // TODO: 2018/6/19 sub

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
