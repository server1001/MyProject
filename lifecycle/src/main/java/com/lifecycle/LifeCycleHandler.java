package com.lifecycle;


import android.os.Handler;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * description ：
 * author : mr.zhao
 * email : zqc0520@aliyun.com
 * date : 2020/11/2 15:15
 */
public class LifeCycleHandler extends Handler implements LifecycleObserver {
    LifecycleOwner owner;


    public LifeCycleHandler(LifecycleOwner owner) {
        this.owner = owner;
        addObserver();
    }

    void addObserver() {
        if (owner == null) {
            throw  new NullPointerException("LifecycleOwner is need init");
        }
        owner.getLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy() {
        //参数为null 会清除所有的callback与message
        removeCallbacksAndMessages(null);

        owner.getLifecycle().removeObserver(this);
    }
}
