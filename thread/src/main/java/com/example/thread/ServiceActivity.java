package com.example.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ServiceActivity extends AppCompatActivity {
    Intent intent ;
    MyService.DebugBinder binder;
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (MyService.DebugBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(String  string){
        Log.e("===========", "event     " + string);
    }

    public void getValue(View view) {
        binder.getValue();
        Log.e("===========", "getValue     " + binder.getValue());
    }

    public void setValue(View view) {
        binder.setValue("123456789");
    }

    public void start(View view) {
        intent = new Intent(ServiceActivity.this, MyService.class);
        intent.putExtra("testValue","testData");
        startService(intent);
    }

    public void stop(View view) {
        stopService(intent);
    }


    public void connect(View view) {
        Intent intent = new Intent(ServiceActivity.this, MyService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    public void disConnect(View view) {
        unbindService(connection);
    }

}