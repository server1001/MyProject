package com.example.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * description ：
 * author : mr.zhao
 * email : zqc0520@aliyun.com
 * date : 2021/1/8 16:16
 */
public class TestTextView extends androidx.appcompat.widget.AppCompatTextView {
    public static final String TAG = TestTextView.class.getSimpleName();

    public TestTextView(@NonNull Context context) {
        super(context);
    }

    public TestTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
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
    protected void onAttachedToWindow() {
        Log.e("==========", TAG + "  onAttachedToWindow  ");

        super.onAttachedToWindow();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
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
        return super.dispatchTouchEvent(event);
    }
}
