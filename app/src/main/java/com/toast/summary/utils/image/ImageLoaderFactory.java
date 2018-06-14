package com.toast.summary.utils.image;

/**
 * 图片加载工厂类
 */

public class ImageLoaderFactory {

    private static ImageLoaderWrapper sImageLoaderWrapper;

    public static ImageLoaderWrapper getLoader() {
        synchronized (ImageLoaderFactory.class) {
            if (sImageLoaderWrapper == null) {
                sImageLoaderWrapper = new GlideImageLoader();
            }
            return sImageLoaderWrapper;
        }
    }

}
