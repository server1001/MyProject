package com.example.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * description ：
 * author : mr.zhao
 * email : zqc0520@aliyun.com
 * date : 2021/1/8 16:43
 */
public class TestViewGroup extends LinearLayout {

    public static final String TAG=TestViewGroup.class.getSimpleName();
    public TestViewGroup(Context context) {
        super(context);
    }

    public TestViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("==========", TAG + "  onTouchEvent   ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("==========", TAG + "  onTouchEvent  ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("==========", TAG + "  onTouchEvent  ACTION_MOVE");

                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("==========", TAG + "  dispatchTouchEvent   ACTION_DOWN");

                break;
            case MotionEvent.ACTION_UP:

                Log.e("==========", TAG + "  dispatchTouchEvent   ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("==========", TAG + "  dispatchTouchEvent   ACTION_MOVE");

                break;
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("==========", TAG + "  onInterceptTouchEvent   ACTION_DOWN");

                break;
            case MotionEvent.ACTION_UP:
                Log.e("==========", TAG + "  onInterceptTouchEvent   ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("==========", TAG + "  onInterceptTouchEvent   ACTION_MOVE");

                break;
        }


        return super.onInterceptTouchEvent(ev);

    }

}
