package com.toast.summary.readhub.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

public interface BaseContract {

    interface BasePresenter<T extends BaseView>{
        void attachView(T view);

        void detachView();
    }

    interface BaseView{
        /**
         * 显示进度中
         */
        void showLoading();

        /**
         * 隐藏进度
         */
        void hideLoading();

        /**
         * 显示请求成功
         */
        void showSuccess();

        /**
         * 失败重试
         */
        void showFailed();

        /**
         * 显示当前网络不可用
         */
        void showNoNet();

        /**
         * 重试
         */
        void onRetry();

        <T> LifecycleTransformer<T> bindToLife();
    }
}
