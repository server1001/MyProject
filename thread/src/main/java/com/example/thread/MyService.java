package com.example.thread;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

public class MyService extends Service {
    String data;
    public static final String TAG=MyService.class.getSimpleName();
    public MyService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        data=intent.getStringExtra("testValue");
        Log.e(TAG,"传入值     "+intent.getStringExtra("testValue"));
        EventBus.getDefault().post("onStartCommand");
        return super.onStartCommand(intent, flags, startId);

    }



    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        data=intent.getStringExtra("testValue");
       return new DebugBinder();
    }

    public class  DebugBinder extends Binder {
        String  getValue(){
            return "value";
        }

        void setValue(String value){
            Log.e("===========","setValue     "+value);

        }

    }
}
