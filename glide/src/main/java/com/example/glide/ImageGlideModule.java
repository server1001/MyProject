package com.example.glide;

import android.content.Context;
import android.os.Environment;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;

import androidx.annotation.NonNull;

/**
 * option ：在AndroidManifest.xml中<application>中添加<meta-data>项，如：
 * <meta-data
 * android:name="com.example.glide.ImageGlideModule"
 * android:value="AppGlideModule"/>
 * description ： glide 加载
 * author : mr.zhao
 * email : zqc0520@aliyun.com
 * date : 2020/11/11 09:53
 */

@GlideModule
public class ImageGlideModule extends AppGlideModule {
    String diskPath="glide";
    @Override
    public boolean isManifestParsingEnabled() {

        return false;
    }

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        super.applyOptions(context, builder);
        int maxSize = 10*1024*1024;
        int diskCache=30*1024*1024;
        //设置内存限制
        builder.setMemoryCache(new LruResourceCache(maxSize));
        //设置缓存目录及大小
        if (Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)){
            builder.setDiskCache(new ExternalCacheDiskCacheFactory(context,"",maxSize));
        }else {
            builder.setDiskCache(new InternalCacheDiskCacheFactory(context,"",maxSize));
        }
        //设置磁盘缓存目录及大小
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context,diskPath,diskCache));


    }
}
