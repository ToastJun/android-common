package com.toast.core.base;

public interface WIBaseView<B> {

    /**
     * 加载数据成功
     * @param b
     */
    void onLoadData(B b);

    /**
     * 加载数据为空
     */
    void onEmptyData();

    /**
     * 加载数据失败
     * @param msg
     */
    void onLoadError(String msg);

    /**
     * 网络错误
     * @param msg
     */
    void onNetError(String msg);
}
