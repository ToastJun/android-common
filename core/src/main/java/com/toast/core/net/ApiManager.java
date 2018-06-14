package com.toast.core.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 提供 retrofit apiService管理
 * Created by wwj on 2018/6/12
 */
public class ApiManager {

    public static final int CONNECT_TIME_OUT_SECONDS = 10 * 1000;
    public static final int READ_TIME_OUT_SECONDS = 10 * 1000;
    public static final int WRITE_TIME_OUT_SECONDS = 10 * 1000;

    /**
     * 创建 Api
     * @param service Api class
     * @param baseUrl base url
     * @param <T>
     * @return
     */
    private static <T> T createService(Class<T> service, String baseUrl) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        // 创建OKhttpclient
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT_SECONDS, TimeUnit.MILLISECONDS)
                .readTimeout(READ_TIME_OUT_SECONDS, TimeUnit.MILLISECONDS)
                .writeTimeout(WRITE_TIME_OUT_SECONDS, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new LoggingInterceptor())
                .build();

        // 创建Retrofit
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();

        return retrofit.create(service);
    }
}
