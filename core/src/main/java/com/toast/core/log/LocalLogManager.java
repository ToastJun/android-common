package com.toast.core.log;

import android.content.Context;

import com.toast.common.utils.AppUtils;
import com.toast.common.utils.FileUtils;
import com.toast.common.utils.TimeUtils;
import com.toast.core.cache.CacheManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 用户日志管理
 */
public class LocalLogManager {

    // 单例
    private static LocalLogManager sLocalLogManager;

    /**
     * 内存缓存的最大条数
     */
    private static final int MAXSIZE_CACHE = 10;

    /**
     * 缓存文件最大个数
     */
    private static final int MAXSIZE_CACHE_FILE = 7;

    /**
     * 内存缓存数据
     */
    private List<String> logCache = new ArrayList<>();

    /**
     * app类型的上下文
     */
    private Context mAppContext;

    /**
     * 缓存文件路径
     */
    private String cacheFilePath;

    /**
     * 日志文件的后缀名
     */
    private static final String FILE_FORMAT = ".log";

    private LocalLogManager(){}

    public static LocalLogManager getInstance() {
        if (sLocalLogManager == null) {
            sLocalLogManager = new LocalLogManager();
        }
        return sLocalLogManager;
    }

    /**
     * 初始化 需要在 application 中初始化
     * @param context
     */
    public static void initLog(Context context) {
        getInstance();
        sLocalLogManager.mAppContext = context;
        sLocalLogManager.cacheFilePath = CacheManager.getTypeCachePath(CacheManager.LOG_CACHE_PATH);
    }

    /**
     *
     * @param logString
     */
    private void cacheLog(String logString) {
        if (mAppContext == null) {
            throw new RuntimeException("call initLog() first");
        }
        L.i("cacheLog------------>开始将日志写入缓存 thread=" + Thread.currentThread().getName());
        logCache.add(logString);

        // 缓存满了 写入到文件
        if (logCache.size() >= MAXSIZE_CACHE) {
            Observable.create( subscriber -> {
                cacheToFile();
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe();
        }
    }

    /**
     * 将缓存中的log写入到文件
     */
    private void cacheToFile() {
        L.i("cacheToFile------------->开始将日志缓存写入文件 thread=" + Thread.currentThread().getName());
        File file = new File(getFilePath());
        if (file.exists()) {
            // 今日日志文件已经存在 追加写入
            saveFileAdd(file.getAbsolutePath(), catLogString(), true);
        } else {
            // 创建今日日志文件 写入
            checkCacheFileValidate();
            saveFile(file.getAbsolutePath(), catLogString());
        }
    }

    /**
     * 强制将缓存写入文件
     */
    private void flushCache2File() {
        if (mAppContext == null) {
            throw new RuntimeException("call initLog() first");
        }
        if (logCache.isEmpty()) {
            return;
        }
        cacheToFile();
    }

    /**
     * 获取当前日期的缓存文件path
     * @return
     */
    private String getFilePath() {
        File file = new File(sLocalLogManager.cacheFilePath, TimeUtils.getToday() + FILE_FORMAT);
        return file.getAbsolutePath();
    }

    /**
     * 检查缓存是否过期
     */
    private void checkCacheFileValidate(){
        List<File> allLogFiles = getAllLogFiles();
        // 判断是否已经超过最大缓存文件数量
        if (allLogFiles.size() >= MAXSIZE_CACHE_FILE) {
            // 删除超过的文件 并为即将写入的文件腾出一个位置
            for(int i = 0; i <= allLogFiles.size() - MAXSIZE_CACHE_FILE; i++) {
                FileUtils.deleteFile(allLogFiles.get(i).getAbsolutePath());
            }
        }
    }

    /**
     * 获取缓存目录下所有的log文件
     * @return
     */
    private List<File> getAllLogFiles() {
        File logDir = new File(cacheFilePath);
        ArrayList<File> vecFile = new ArrayList<>();
        File[] subFiles = logDir.listFiles();

        if (subFiles == null) {
            return vecFile;
        }

        for (File file : subFiles) {
            // 判断是否为文件
            if (file.isFile()) {
                // 判断是否为.log结尾
                if (file.getAbsolutePath().trim().toLowerCase().endsWith(FILE_FORMAT)) {
                    vecFile.add(file);
                }
            }
        }
        return vecFile;
    }

    /**
     * 组装缓存日志字符串格式
     * @return
     */
    private synchronized String catLogString(){
        try {
            StringBuffer sb = new StringBuffer();
            ArrayList<String> logTempList = new ArrayList<>();
            logTempList.addAll(logCache);
            for (String log : logTempList) {
                sb.append("----------------------------------")
                        .append("\n")
                        .append(TimeUtils.getNowTimeString())
                        .append("\n")
                        .append(log)
                        .append("\n\n");
            }
            logCache.clear();
            return sb.toString();
        } catch (Exception e) {
            return e.getStackTrace().toString();
        }
    }

    private synchronized void saveFile(String filePath, String content) {
        // 在文件头部添加一些手机设备信息
        content = addFileHead(content);
        saveFileAdd(filePath, content, false);
    }

    /**
     * 已追加写入形式写入文件
     * @param filePath
     * @param content
     * @param add 是否追加写入
     */
    private synchronized void saveFileAdd(String filePath, String content, boolean add) {
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            if (!FileUtils.createOrExistsDir(file)) {
                return;
            }
            fileWriter = new FileWriter(file, add);
            fileWriter.write(content);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String addFileHead(String msg) {
        StringBuffer sb = new StringBuffer();
        sb.append(AppUtils.getPhoneSysInfo())
                .append("\n")
                .append(msg);
        return sb.toString();
    }
}
