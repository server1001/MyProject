package com.example.http;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class HttpActivity extends AppCompatActivity {
    Dispatcher  dispatcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);



    }

    public void click(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Login login = new Login("123456");
                try {
                    post(new Gson().toJson(login));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    class Test{
        String stbId;
        String deviceId;
        String productId;
        int state;

        public Test(String stbId, String deviceId, String productId, int state) {
            this.stbId = stbId;
            this.deviceId = deviceId;
            this.productId = productId;
            this.state = state;
        }
    }

    public void report(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Test test = new Test("123456","123456","123456",0);
                try {
                    report(new Gson().toJson(test));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private  final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
    public  void post(String json) throws IOException {
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
                .addHeader("appVersion", "0.0.0")
                .addHeader("appSecret", "2163d491854a480ea5c3e1ca75f764d7")

                .url(RequestUrlManager.BASE_URL.concat(RequestUrlManager.GET_TOKEN_URL))
                .post(body)
                .build()
                ;
         client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("===========",response.body().toString());
            }
        });
    }

    String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MDY3MjAxMzQsImV4dGVuZEluZm8iOnsicGhvbmUiOm51bGwsInBhc3NJZCI6IjQxOTA3MjM5NDI0MzYyMTg4OCJ9LCJ1c2VyX25hbWUiOiI0MTkwNzIzOTQyNDM2MjE4ODgiLCJqdGkiOiIwODFhNzYxZi1iNjRlLTQxODUtOWQ1My03ZTIxYmY3YzlmZTgiLCJjbGllbnRfaWQiOiJoZHNiIiwic2NvcGUiOlsiYWxsIl19.FVlLaQVYWmELfm5V8hvRSs2PELCuNNRJ6qPO-M8d4tI";


    public  void report(String json) throws IOException {
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
                .addHeader("appVersion", "0.0.0")
                .addHeader("appSecret", "2163d491854a480ea5c3e1ca75f764d7")
                .addHeader("authorization",
        "bearer"
                .concat(" ")
                .concat(token))
                .url(RequestUrlManager.BASE_URL.concat(RequestUrlManager.REPORT_DATA_URL))
                .post(body)
                .build()
                ;
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.e("===========", response.body().toString());
            }
        });
    }



    class Login {
        String thirdId;

        public Login(String thirdId) {
            this.thirdId = thirdId;
        }
    }
}
