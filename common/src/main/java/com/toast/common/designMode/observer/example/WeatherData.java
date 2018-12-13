package com.toast.common.designMode.observer.example;

import com.toast.common.designMode.observer.Observable;
import com.toast.common.designMode.observer.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 天气类
 */
public class WeatherData implements Observable<String>{

    private List<Observer<String>> observers = new ArrayList<>();

    private String currentContent = "";

    @Override
    public void registerObserver(Observer<String> observer, boolean register) {
        if (observer == null) {
            return;
        }
        if (register) {
            observers.add(observer);
        } else {
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObservers() {
        // 当温度内容变更时,通知观察者
        for (Observer<String> observer : observers) {
            observer.onEvent(currentContent);
        }
    }

    /**
     * 更新当前温度显示内容
     * @param currentContent
     */
    public void updateContent(String currentContent) {
        this.currentContent = currentContent;
        notifyObservers();
    }
}
