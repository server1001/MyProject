package com.komect.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.komect.test.photoview.PhotoView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * description ：
 * author : mr.zhao
 * email : zqc0520@aliyun.com
 * date : 2020/12/8 09:50
 */
public class ImagesLookPagerAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<String> mList;
    private int positions;

    public ImagesLookPagerAdapter(Context context, ArrayList<String> mList, int position) {
        this.context = context;
        this.mList = mList;
        this.positions = position;
    }



    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    private boolean isFull = false;

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        if (mList != null && position < mList.size()) {
            String resId = mList.get(position);
            if (resId != null) {
                PhotoView popupImages = new PhotoView(context);
//                ViewPager.LayoutParams layoutParams= new ViewPager.LayoutParams();
//                layoutParams.width= (int) getPageWidth(position);
//                popupImages.setLayoutParams(layoutParams);
                Glide.with(context).load(mList.get(position)).into(popupImages);
                popupImages.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

//                        if (!isFull){
//                            isFull = true;
//                            ImagesLook.getInstance().closeActionbar();
//                        }else {
//                            isFull = false;
//                            ImagesLook.getInstance().showActionbar();
//                        }
                        //ToastUtils.showShort(mList.get(positions));
                        ImagesLook.getInstance().close();
                    }
                });

                //此处假设所有的照片都不同，用resId唯一标识一个itemView；也可用其它Object来标识，只要保证唯一即可
                popupImages.setTag(resId);

                container.addView(popupImages);
                return popupImages;
            }
        }
        return null;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        if (object != null) {
            int count = container.getChildCount();
            for (int i = 0; i < count; i++) {
                View childView = container.getChildAt(i);
                if (childView == object) {
                    container.removeView(childView);
                    break;
                }
            }
        }
    }
}

