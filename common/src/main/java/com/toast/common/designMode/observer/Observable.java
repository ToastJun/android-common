package com.toast.common.designMode.observer;

/**
 * 被观察者接口
 * @param <T>
 */
public interface Observable<T> {
    // 注册或移除观察者
    void registerObserver(Observer<T> observer, boolean register);

    void notifyObservers();
}
