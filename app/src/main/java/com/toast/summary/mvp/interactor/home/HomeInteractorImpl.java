package com.toast.summary.mvp.interactor.home;

import android.util.Log;

import com.toast.summary.mvp.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/6/6.
 */

public class HomeInteractorImpl implements HomeInteractor{
    @Override
    public List<UserBean> findItems(OnFindFinishedListener listener) {
        final List<UserBean> items =new ArrayList<>();
        // 使用rxjava
        Observable.create(new ObservableOnSubscribe<UserBean>() {
            @Override
            public void subscribe(ObservableEmitter<UserBean> e) throws Exception {
                e.onNext(new UserBean("第一"));
            }
        }).subscribeOn(Schedulers.io())
                .subscribe(new Observer<UserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("onSubscribe", "onSubscribe");
                    }

                    @Override
                    public void onNext(UserBean value) {
                        Log.d("onNext", value.name);
                        items.add(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return items;
    }
}
