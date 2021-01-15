package com.komect.crash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CrashActivity extends AppCompatActivity {

    String str=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash);
        new Thread(new Runnable() {
            @Override
            public void run() {
                str.substring(2);

            }
        }).start();
    }
}
