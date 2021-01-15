package com.example.thread;

import android.os.AsyncTask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * description ：
 * author : 赵青春
 * email : 1458174550@qq.com
 * date : 2020/9/16 10:41
 */
public class PrintlnThread {

    Lock lock = new ReentrantLock();
    private int state = 0;

    public void println() {
        threadA.start();
        threadB.start();
    }

    Thread threadA = new Thread("ThreadA") {
        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 10; ) {
                lock.lock();
                try {
                    while (state % 2 == 0) {
                        System.out.println("ThreadA    " + i);
                        state++;
                        i++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    };

    Thread threadB = new Thread("threadB") {
        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 10; ) {
                lock.lock();
                while (state % 2 == 1) {
                    System.out.println("threadB    " + i);
                    state++;
                    i++;
                }

                lock.unlock();
            }
        }
    };


    Semaphore semaphore = new Semaphore(0);

    Semaphore semaphore0 = new Semaphore(1);
    Semaphore semaphore02 = new Semaphore(3);

    Executor executor = Executors.newCachedThreadPool();

    ExecutorService executorService = new ThreadPoolExecutor(1, 1,
            1, TimeUnit.MINUTES, new SynchronousQueue<Runnable>());

    Runnable runnable = new Runnable() {
        @Override
        public void run() {

        }
    };

    void test() {
        executorService.execute(runnable);

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "null";
            }
        };

      Future<String> future= executorService.submit(callable);
      future.isDone();
        try {
            future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    void test01(){
        AsyncTask asyncTask=new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
            }

            @Override
            protected void onProgressUpdate(Object[] values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onCancelled(Object o) {
                super.onCancelled(o);
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
            }
        };
        asyncTask.execute("");
    }
}
