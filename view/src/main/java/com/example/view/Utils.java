package com.example.view;

import android.util.Log;

/**
 * description ：
 * author : mr.zhao
 * email : zqc0520@aliyun.com
 * date : 2020/10/19 15:28
 */
public class Utils {

    int num = 0;
    static int num01=1;

    public void show() {

    }

    public Test getTest() {
        return new Test();
    }

    //成员内部类
    class Test {

        public void log() {
            Log.e("========", "    " + num);
        }
    }

    public Utils test01() {
        //局部内部类 定义在方法内
        class Test01 extends Utils {
            public void test02() {

            }
        }
        return new Test01();
    }

    public static class Class01{
        void test(){
            Log.e("==========","   "+num01);
        }
    }

}
