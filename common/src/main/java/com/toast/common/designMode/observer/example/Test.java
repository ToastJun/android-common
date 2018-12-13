package com.toast.common.designMode.observer.example;

import com.toast.common.designMode.observer.Observer;

import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        Observer myObserver = new Observer<String>(){

            @Override
            public void onEvent(String s) {
                System.out.println("myObserver onEvent="+s);
            }
        };

        weatherData.registerObserver(myObserver, true);

        weatherData.updateContent("当前温度是5摄氏度");
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        weatherData.updateContent("当前温度是15摄氏度");
    }

}
