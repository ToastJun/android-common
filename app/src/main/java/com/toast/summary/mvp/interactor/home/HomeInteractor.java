package com.toast.summary.mvp.interactor.home;

import com.toast.summary.mvp.bean.UserBean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/6.
 */

public interface HomeInteractor {

    interface OnFindFinishedListener<T>{

        void onFailed();

        void onSuccess(List<T> items);
    }

    List<UserBean> findItems(OnFindFinishedListener listener);
}
