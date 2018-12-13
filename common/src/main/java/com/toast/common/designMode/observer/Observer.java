package com.toast.common.designMode.observer;

/**
 * 观察者
 * @param <T>
 */
public interface Observer<T> {

    void onEvent(T t);
}
