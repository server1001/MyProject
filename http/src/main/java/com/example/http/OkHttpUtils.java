package com.example.http;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * description ：
 * author : mr.zhao
 * email : zqc0520@aliyun.com
 * date : 2020/11/28 10:29
 */
public class OkHttpUtils {
    MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    static OkHttpUtils instance;
    OkHttpClient client;
    HttpLoggingInterceptor httpLoggingInterceptor;

    public static OkHttpUtils getInstance() {
        if (instance == null) {
            synchronized (OkHttpUtils.class) {
                if (instance == null) {
                    instance = new OkHttpUtils();
                }
            }
        }
        return instance;
    }

    private void initOkHttp() {
        if (client == null) {
            httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client = new OkHttpClient.Builder()
                    .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
                    .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                    .addInterceptor(httpLoggingInterceptor)
                    .build();
        }
    }

    private Response post(String json) throws IOException {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                .addInterceptor(httpLoggingInterceptor)
                .build();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json;charset=UTF-8")
                .addHeader("pCode", "000000")
                .addHeader("appId", "hdsb")
                .addHeader("appKey", "android")
                .addHeader("appSecret", "2163d491854a480ea5c3e1ca75f764d7")
                .url(RequestUrlManager.BASE_URL.concat(RequestUrlManager.GET_TOKEN_URL))
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response;
    }
}
