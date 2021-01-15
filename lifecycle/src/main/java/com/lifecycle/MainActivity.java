package com.lifecycle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    LifeCycleHandler lifeCycleHandler=new LifeCycleHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TestViewModel testViewModel=  ViewModelProviders.of(MainActivity.this).get(TestViewModel.class);

    }
}
