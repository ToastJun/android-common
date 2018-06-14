package com.toast.core.net.bean;

/**
 * Created by wwj on 2018/6/13
 */
public class BaseNetBean<T> {
    public int code;

    public boolean successful;

    public String error;

    public T data;
}
