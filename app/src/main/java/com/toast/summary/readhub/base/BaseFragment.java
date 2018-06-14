package com.toast.summary.readhub.base;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.RxFragment;

public abstract class BaseFragment extends RxFragment implements BaseContract.BaseView{
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void showFailed() {

    }

    @Override
    public void showNoNet() {

    }

    @Override
    public void onRetry() {

    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return null;
    }
}
