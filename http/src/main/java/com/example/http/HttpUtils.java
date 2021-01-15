package com.example.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * description ：
 * author : mr.zhao
 * email : zqc0520@aliyun.com
 * date : 2020/12/31 15:55
 */
public class HttpUtils {

    public static HttpURLConnection get(String url) {
        HttpURLConnection connection = null;
        try {
            URL mTrl=new URL(url);
            connection= (HttpURLConnection) mTrl.openConnection();
            connection.setReadTimeout(5*1000);
            connection.setConnectTimeout(5*1000);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("","");
            connection.setDoInput(true);
            connection.setDoOutput(true);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
