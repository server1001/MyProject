package com.example.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import dalvik.system.DexClassLoader;

import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    TestTextView tv;
    TestViewGroup tvg_test;
    public static final String TAG=MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvg_test=findViewById(R.id.tvg_test);
      tv=  findViewById(R.id.tv_test);
//        tvg_test.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("==========",TAG+"  点击事件");
//            }
//        });
//      tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("==========",TAG+"  点击事件");
//
//            }
//        });
//      tv.setOnTouchListener(new View.OnTouchListener() {
//          @Override
//          public boolean onTouch(View v, MotionEvent event) {
//              return false;
//          }
//      });

        int test[]=new int[]{1,2,3,4,5,6,7};


      //  test(t);
        test001();
        test002();
    }

    void test001(){
        LogUtils.logd(MainActivity.class,"ceshi001");
    }
    void test002(){
        LogUtils.loge(MainActivity.class,"ceshi002");
    }
    String t[]=new String[]{"a","b","c","d","e","f","g","h","i","j","k","l"};

    void test( String [] strings){
//        String  thumb[]=new String[strings.length];
//        for (int i = 0; i < strings.length; i++) {
//            thumb[i]=strings[strings.length-1-i];
//        }
//        strings=thumb.clone();
//        for (int i = 0; i < strings.length; i++) {
//            System.out.println("日志打印01    "+strings[i]);
//        }

        for (int i = 0; i < strings.length/2; i++) {
            String temp=strings[i];
            strings[i]=strings[strings.length-1-i];
            strings[strings.length-1-i]=temp;
        }
        for (int i = 0; i < strings.length; i++) {
            System.out.println("日志打印01    "+i+strings[i]);
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("==========",TAG+"  onTouchEvent   ACTION_DOWN");

                break;
            case MotionEvent.ACTION_UP:
                Log.e("==========",TAG+"  onTouchEvent  ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("==========",TAG+"  onTouchEvent  ACTION_MOVE");

                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("==========",TAG+"  dispatchTouchEvent   ACTION_DOWN");

                break;
            case MotionEvent.ACTION_UP:
                Log.e("==========",TAG+"  dispatchTouchEvent   ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("==========",TAG+"  dispatchTouchEvent   ACTION_MOVE");

                break;
        }
        return super.dispatchTouchEvent(ev);
    }



}