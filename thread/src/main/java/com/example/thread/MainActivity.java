package com.example.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.MessageQueue;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AppCompatActivity;

@Route(path = "/thread/122")
public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ReentrantLock
//                reentrantLock;
//        View view = null;
//        view.post(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
//
//        Handler handler = new Handler(getMainLooper());
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
//
//        Handler handler1=new Handler();
//
//        Looper.loop();
//
//        PrintlnThread printlnThread = new PrintlnThread();
//        printlnThread.println();
//        AtomicInteger atomicInteger = new AtomicInteger(0);
//        atomicInteger.getAndDecrement();

      //  childHandler();
//        mainHandler();
        //     childHandler2();
        test();
    }


    Handler handler;


    private void mainHandler() {

        MessageQueue.IdleHandler idleHandler = new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                return false;
            }
        };
        Looper.getMainLooper().getQueue().addIdleHandler(idleHandler);
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        }).start();
    }

    private void childHandler() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler = new Handler(getMainLooper());
                handler.sendEmptyMessage(0);
            }
        }).start();
    }

    private void childHandler2() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                handler = new Handler();
                handler.sendEmptyMessage(0);
                Looper.loop();
            }
        }).start();
    }

    void test() {
        //自定义线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 3, 60 * 1000,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(6),
             new Test());


        for (int i = 0; i <20 ; i++) {
            Log.e("==========","添加第   "+i+  "   任务");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadPoolExecutor.execute(new MyRun("线程   "+i));
        }



    }

    class MyRun implements Runnable {
        String  name;

        public MyRun(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.e("==========","执行    "+ name+"        "+Thread.currentThread().getName() );

        }
    }

    class Test implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            MyRun myRun= (MyRun) executor.getQueue().iterator().next();
            Log.e("==========","jujue       "+myRun.name);

        }
    }
}