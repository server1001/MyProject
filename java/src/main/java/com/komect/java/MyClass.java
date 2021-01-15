package com.komect.java;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClass {


    public static void main(String[] args) {
        int test[]=new int[]{1,2,3,4,5,6,7};
        int thumb[]=new int[test.length];
        for (int i = 0; i < test.length-1; i++) {
            thumb[i]=test[test.length-i];
        }
        test=thumb.clone();
        for (int i = 0; i < test.length-1; i++) {
            System.out.println("日志打印01    "+test[i]);
        }

//        ClassLoadDebug loadDebug=new ClassLoadDebug("D:\\java");
//        try {
//            Class clazz=loadDebug.loadClass("com.komect.hook.ClassLoadTest");
//            Object o=clazz.newInstance();
//            System.out.println("日志打印   "+o.getClass().getClassLoader());
//            Method method=clazz.getDeclaredMethod("test",String.class);
//            method.invoke(o,"debug");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
    }

    void test01(){

    }

    void test(){
        //        ClassLoader classLoader=MyClass.class.getClassLoader();
//        while (classLoader!=null){
//            System.out.println("日志打印    "+classLoader);
//            classLoader=classLoader.getParent();
//
//        }
//        System.out.println("日志打印    "+System.getProperty("sun.boot.class.path"));
//        System.out.println("日志打印01    "+System.getProperty("java.ext.dirs"));


        ClassLoadDebug loadDebug=new ClassLoadDebug("D:\\java");
        try {
            Class clazz=loadDebug.loadClass("com.komect.hook.ClassLoadTest");
            Object o=clazz.newInstance();
            System.out.println("日志打印   "+o.getClass().getClassLoader());
            Method method=clazz.getDeclaredMethod("test",String.class);
            method.invoke(o,"debug");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}