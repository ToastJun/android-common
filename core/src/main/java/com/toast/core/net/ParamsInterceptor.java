package com.toast.core.net;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wwj on 2018/6/12
 */
public class ParamsInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();
        HttpUrl.Builder builder = originalHttpUrl.newBuilder();

        builder.addQueryParameter("app_version", "1.0");
        builder.addQueryParameter("add_params", "haha");

        HttpUrl url = builder.build();
        Request request = original.newBuilder().url(url).build();

        StringBuffer sb = new StringBuffer();
        sb.append("请求数据: " + request.url() + "&" + bodyToString(request))
                .append("\n\n");

        return null;
    }

    private String bodyToString(Request request) {

        return null;
    }
}
