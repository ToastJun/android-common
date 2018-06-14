package com.toast.core.net;

import android.util.Log;

import java.io.IOException;
import java.net.URLDecoder;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * retrofilt日志拦截器
 * Created by wwj on 2018/6/12
 */
public class LoggingInterceptor implements Interceptor{
    private final String TAG = "LoggingInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long t1 = System.nanoTime();
        Response response = chain.proceed(request);

        ResponseBody responseBody = response.body();

        String responseBodyString = response.body().string();

        Response newResponse = response.newBuilder().body(ResponseBody.create(responseBody.contentType(), responseBodyString.getBytes())).build();

        if (!String.valueOf(newResponse.code()).startsWith("2")) {
            StringBuffer sb = new StringBuffer();
            sb.append("请求失败,返回码为" + newResponse.code() + "\n" +
                    "请求数据: " + response.request().url() + "&" + bodyToString(request))
                    .append("\n\n");
            // TODO: 2018/6/12
            // L.logFile(sb.toString());
            Log.i(TAG, sb.toString());
        }

        long t2 = System.nanoTime();

        StringBuffer sb = new StringBuffer();
        sb.append("请求数据: " + response.request().url() + "&" + bodyToString(request))
                .append("\n\n")
                .append("返回数据: " + responseBodyString)
                .append("\n\n")
                .append("耗时: " + (t2 - t1) / 1e6d + "毫秒" + request.url());
        Log.i(TAG, sb.toString());

        return newResponse;
    }

    private String bodyToString(Request request) {
        try {
            Request copy = request.newBuilder().build();
            Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return URLDecoder.decode(buffer.readUtf8(), "utf-8");
        } catch (IOException e) {
            Log.i(TAG, e.getMessage());
            e.printStackTrace();
            return "请求参数解析失败";
        }
    }
}
