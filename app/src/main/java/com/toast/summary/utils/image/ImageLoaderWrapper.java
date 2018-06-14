package com.toast.summary.utils.image;

import android.widget.ImageView;

import java.io.File;

/**
 * 图片加载接口
 */

public interface ImageLoaderWrapper {

    public void displayImage(ImageView imageView, File file, DisplayOptions displayOptions);

    public void displayImage(ImageView imageView, String url, DisplayOptions displayOptions);

    public void displayImage(ImageView imageView, String url);


    public static class DisplayOptions {
        /**
         * 加载中图片资源id
         */
        public int loadingResId;
        /**
         * 加载出错图片资源id
         */
        public int errorResId;
    }
}

