package com.example.thread;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * description ：
 * author : 赵青春
 * email : 1458174550@qq.com
 * date : 2020/9/25 16:50
 */
public class AppApplication01 extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
    }
}
