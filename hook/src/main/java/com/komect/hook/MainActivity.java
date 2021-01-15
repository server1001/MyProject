package com.komect.hook;

import androidx.appcompat.app.AppCompatActivity;
import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

import android.os.Bundle;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ClassLoader classLoader=MainActivity.class.getClassLoader();
        while (classLoader!=null){
            System.out.println(classLoader.toString());
            classLoader=classLoader.getParent();
        }


    }


    void  hook(View view){
        try {
            Method method=View.class.getDeclaredMethod("getListenerInfo");
            method.setAccessible(true);
            Object listenerInfo=method.invoke(view);
            Class listenerInfoClz=Class.forName("android.view.ListenerInfo");
            Field mOnClickListener=listenerInfoClz.getDeclaredField("mOnClickListener");
            mOnClickListener.setAccessible(true);
            View.OnClickListener clickListener= (View.OnClickListener) mOnClickListener.get(listenerInfo);

            View.OnClickListener hookedOnClickListener =new HookOnClickListener(clickListener);
            mOnClickListener.set(listenerInfo, hookedOnClickListener);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    class HookOnClickListener implements View.OnClickListener {
        View.OnClickListener clickListener;

        public HookOnClickListener(View.OnClickListener clickListener) {
            this.clickListener = clickListener;
        }

        @Override
        public void onClick(View v) {
            if (clickListener!=null){
                clickListener.onClick(v);
            }
        }
    }

}