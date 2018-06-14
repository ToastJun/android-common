package com.toast.core.net;

import android.util.Log;

import com.toast.core.net.bean.BaseNetBean;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wwj on 2018/6/13
 */
public class RxHelper {

    /**
     * 将 通用的网络返回类 转换成 其中的data
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<BaseNetBean<T>, T> handleResult(){
        return baseNetBeanObservable -> baseNetBeanObservable.flatMap(
                tBaseNetBean -> {
                    Log.i("RxHelper", tBaseNetBean.code + "");
                    if (tBaseNetBean.code == 0) {
                        return createData(tBaseNetBean.data);
                    } else {
                        return Observable.error(new Exception(tBaseNetBean.error));
                    }
                }
        ).compose(switchSchedulers());
    }

    private static <T> Observable<T> createData(final T data){
        return Observable.create(subscriber ->  {
                try {
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
        });
    }

    public static <T> Observable.Transformer<T, T> switchSchedulers() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
