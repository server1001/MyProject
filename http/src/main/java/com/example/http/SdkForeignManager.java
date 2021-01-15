package com.example.http;

import android.content.Context;

/**
 * description ：
 * author : mr.zhao
 * email : zqc0520@aliyun.com
 * date : 2020/11/30 10:51
 */
public class SdkForeignManager {
    Context context;


    public static SdkForeignManager instance;

    public static SdkForeignManager getInstance() {
        if (instance==null){
            synchronized (SdkForeignManager.class){
                if (instance==null){
                    instance=new SdkForeignManager();
                }
            }
        }
        return instance;
    }
    public Context getContext() {
        return context;
    }

    public void init(Context context){
        this.context =context;
    }
}
