package com.toast.summary.bean;

public class MainAppBean {
    /**
     * 子项目app的名称
     */
    public String appName;

    /**
     * 子项目app的描述
     */
    public String desc;

    /**
     * app的本地资源图片
     */
    public int resId;

    /**
     * app的网络资源图片
     */
    public String resUrl;

    public MainAppBean() {
    }

    public MainAppBean(String appName, String desc, int resId, String resUrl) {
        this.appName = appName;
        this.desc = desc;
        this.resId = resId;
        this.resUrl = resUrl;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    @Override
    public String toString() {
        return "MainAppBean{" +
                "appName='" + appName + '\'' +
                ", desc='" + desc + '\'' +
                ", resId=" + resId +
                ", resUrl='" + resUrl + '\'' +
                '}';
    }
}
