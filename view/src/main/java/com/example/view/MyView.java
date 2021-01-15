package com.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * description ：
 * author : mr.zhao
 * email : zqc0520@aliyun.com
 * date : 2020/10/20 17:47
 */
public class MyView extends ViewGroup {
    View childView;

    public MyView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        boolean result;
        if (onInterceptTouchEvent(ev)) {//是否拦截当前事件
            result = onTouchEvent(ev);//true 交由当前view处理事件
        } else {
            result = childView.dispatchTouchEvent(ev);//false 递归子view dispatchTouchEvent
        }
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
