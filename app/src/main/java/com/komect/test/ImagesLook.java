package com.komect.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import java.util.ArrayList;

import androidx.viewpager.widget.ViewPager;

/**
 * description ：
 * author : mr.zhao
 * email : zqc0520@aliyun.com
 * date : 2020/12/8 09:49
 */
public class ImagesLook {
  //  private TextView popupTvPageNum;
    private PopupWindow popupWindow;
    private int position;
    private ArrayList<String> mList;
    private View rootVew;
    private ViewPager popupViewPager;
    private ImagesLookPagerAdapter adapter;

    public static ImagesLook getInstance() {
        return MenuUtilHolder.INSTANCE;
    }

    /**
     * 弹起 popupWindow
     * 通过路径显示
     *
     * @param context context
     * @param parent  parent
     */
    public void show(Context context, View parent, final ArrayList<String> mList, final int position) {
        this.mList = mList;
        this.position = position;
        _createView(context);
        if (popupWindow != null && !popupWindow.isShowing()) {
            popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        }
    }

    /**
     * 创建 popupWindow 内容
     *
     * @param context context
     */
    @SuppressLint("InflateParams")
    private void _createView(final Context context) {
        rootVew = LayoutInflater.from(context).inflate(R.layout.layout_popup_image, null);
        popupWindow = new PopupWindow(rootVew,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        //设置为失去焦点 方便监听返回键的监听
        popupWindow.setFocusable(false);
        popupWindow.setClippingEnabled(false);
        // 如果想要popupWindow 遮挡住状态栏可以加上这句代码
        //popupWindow.setClippingEnabled(false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);
        initLayout(context);
    }

    /**
     * 初始化 view
     */
    private IndicatorView mIndicatorView;

    @SuppressLint("SetTextI18n")
    private void initLayout(Context context) {
        popupViewPager = rootVew.findViewById(R.id.popupViewPager);
     //   popupTvPageNum = rootVew.findViewById(R.id.popupTvPageNum);
        mIndicatorView=rootVew.findViewById(R.id.indicatorView);
        adapter = new ImagesLookPagerAdapter(context, mList, position);

        popupViewPager.setAdapter(adapter);
        mIndicatorView.setViewPager(popupViewPager);
        int page = position + 1;
    //    popupTvPageNum.setText(page + "/" + mList.size());
        popupViewPager.setCurrentItem(position);

        popupViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onPageSelected(int i) {
                int page = i + 1;
          //      popupTvPageNum.setText(page + "/" + mList.size());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }

    /**
     * 关闭popupWindow
     */

    public void close() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }





    private static class MenuUtilHolder {
        @SuppressLint("StaticFieldLeak")
        static ImagesLook INSTANCE = new ImagesLook();
    }

}

