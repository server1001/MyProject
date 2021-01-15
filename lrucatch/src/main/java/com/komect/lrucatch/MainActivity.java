package com.komect.lrucatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.LruCache;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    HashMap<String ,String > map=new HashMap();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < 10; i++) {
            lruCache.put(i,"当前输出   "+i);
        }
        System.out.println("savedInstanceState = " + lruCache);
        System.out.println(" 00000  "+System.getProperty("sun.boot.class.path"));
    }

    LruCache <Integer,String >lruCache=new LruCache<Integer, String>(5){
        @Override
        protected int sizeOf(Integer key, String value) {
            return super.sizeOf(key, value);
        }

        @Override
        protected void entryRemoved(boolean evicted, Integer key, String oldValue, String newValue) {
            super.entryRemoved(evicted, key, oldValue, newValue);
        }

        @Override
        protected String create(Integer key) {
            return super.create(key);
        }
    };
}