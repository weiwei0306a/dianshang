package com.bw.xiangmu.Utils;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @Auther: len
 * @Date: 2019/2/26 09:33:49
 * @Description:
 */
//Get封装
public class OkhttpUtils {
    private static OkHttpClient okHttpClient;

    //单例模式
    private OkhttpUtils() {

    }

    public static OkHttpClient getIntstanse() {
        if (okHttpClient == null) {
            synchronized (OkhttpUtils.class) {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient();
                }
            }
        }
        return okHttpClient;
    }

    public static void HttpGet(String url, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    public static void HttpPost(String url, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        FormBody body = new FormBody.Builder().build();
        Request request = new Request.Builder()
                .method("POST", body)
                .url(url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}

