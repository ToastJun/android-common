package com.toast.core.cache;

import android.os.Environment;

import com.toast.common.utils.FileUtils;
import com.toast.common.utils.Utils;

import java.io.File;

/**
 * app缓存管理类
 */
public class CacheManager {

    /**
     * 分享缓存
     */
    public static final int SHARE_CACHE_PATH = 1;

    /**
     * 通用缓存
     */
    public static final int COMMON_CACHE_PATH = 2;

    /**
     * 日志缓存
     */
    public static final int LOG_CACHE_PATH = 3;

    /**
     * 数据库缓存
     */
    public static final int DB_CACHE_PATH = 4;

    /**
     * 图片合成缓存
     */
    public static final int PIC_PUZZLE_PATH = 5;

    public static String getAppCachePath(){
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return Utils.getContext().getExternalCacheDir().getAbsolutePath();
        } else {
            return Utils.getContext().getCacheDir().getAbsolutePath();
        }
    }

    public static String getTypeCachePath(int cacheType) {
        switch (cacheType) {
            case SHARE_CACHE_PATH:
                return getAppCachePath() + File.separator + "shareCache";

            case COMMON_CACHE_PATH:
                return getAppCachePath() + File.separator + "commonCache";

            case LOG_CACHE_PATH:
                return getAppCachePath() + File.separator + "logCache";

            case DB_CACHE_PATH:
                return getAppCachePath() + File.separator + "dbCache";

            case PIC_PUZZLE_PATH:
                return getAppCachePath() + File.separator + "picPuzzle";

                default:
                    return "";
        }
    }

    /**
     * 清除一般缓存目录
     * 包括通用缓存,分享下载的图片缓存
     */
    public static void clearCommonCache(){
        FileUtils.deleteDir(getTypeCachePath(COMMON_CACHE_PATH));
        FileUtils.deleteDir(getTypeCachePath(SHARE_CACHE_PATH));
        FileUtils.deleteDir(getTypeCachePath(PIC_PUZZLE_PATH));
    }

    /**
     * 清除日志缓存
     */
    public static void clearLogCache(){
        FileUtils.deleteDir(getTypeCachePath(LOG_CACHE_PATH));
    }
}
