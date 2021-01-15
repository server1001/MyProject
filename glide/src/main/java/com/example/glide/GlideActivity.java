package com.example.glide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.cache.LruResourceCache;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class GlideActivity extends AppCompatActivity {
    public static final String TAG=GlideActivity.class.getSimpleName();
    ImageView imageView;
    String url="https://www.wanandroid.com/blogimgs/90c6cc12-742e-4c9f-b318-b912f163b8d0.png";
    LruResourceCache lruResourceCache;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        EventBus.getDefault().register(this);
        imageView=findViewById(R.id.image);
        ImageLoader<String> imageLoader=new ImageLoader<String >();
        imageLoader.with(this).setLoadParams(url).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

//    public void intent(View view){
//        startActivity(new Intent(this, MainActivity.class));
//    }



    /**
     * @param event 消息事件对象
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribe(String event){
        //消息处理

    }
}