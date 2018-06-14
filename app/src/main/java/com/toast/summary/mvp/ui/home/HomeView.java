package com.toast.summary.mvp.ui.home;

import java.util.List;

/**
 * Created by Administrator on 2018/6/6.
 */

public interface HomeView<T> {

    void showProgress();

    void hideProgress();

    void setItems(List<T> items);

    void showMessage(T t);
}
