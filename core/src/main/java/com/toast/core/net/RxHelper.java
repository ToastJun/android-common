package com.toast.core.net;

import android.util.Log;

import com.toast.core.net.bean.BaseNetBean;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by wwj on 2018/6/13
 */
public class RxHelper {

    public static <T>Observable.Transformer<BaseNetBean<T>, T> handleResult(){
        return new Observable.Transformer<BaseNetBean<T>, T>(){

            @Override
            public Observable<T> call(final Observable<BaseNetBean<T>> baseNetBeanObservable) {
                return baseNetBeanObservable.flatMap(new Func1<BaseNetBean<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(BaseNetBean<T> tBaseNetBean) {
                        Log.i("RxHelper", tBaseNetBean.code + "");
                        if (tBaseNetBean.code == 0) {
                            return createData(tBaseNetBean.data);
                        } else {
                            return Observable.error(new Exception(tBaseNetBean.error));
                        }
                    }
                }).subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    private static <T> Observable<T> createData(final T data){
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
