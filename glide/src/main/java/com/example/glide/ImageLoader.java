package com.example.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * description ：图片加载分发类
 * author : mr.zhao
 * email : zqc0520@aliyun.com
 * date : 2020/11/11 10:49
 */
public class ImageLoader<T> {
    private Context mContext;
    String imageUrl;
    Bitmap bitmap;
    int resourceId;
    T loadParams;



    public ImageLoader setLoadParams(T loadParams) {
        this.loadParams = loadParams;
        return this;
    }

    public ImageLoader with(Context context) {
        this.mContext = context;
        return this;
    }


    public void into(ImageView imageView) {
        Glide.with(mContext).load(loadParams).into(imageView);
    }

//    public void into(ImageView imageView){
//        Glide.with(mContext).load(imageUrl).into(imageView);
//    }
//
//    public void into(ImageView imageView){
//        Glide.with(mContext).load(imageUrl).into(imageView);
//    }
}
