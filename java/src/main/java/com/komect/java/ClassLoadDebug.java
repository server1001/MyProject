package com.komect.java;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ClassLoadDebug extends ClassLoader {

    String path;


    public ClassLoadDebug(String path) {
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String s) throws ClassNotFoundException {
        Class clazz=null;
        byte [] data=loadClassData(s);
        if (data==null){
            throw  new ClassNotFoundException(s+" is  not find");
        }else {
            clazz=defineClass(s,data,0,data.length);
        }
        return clazz;
    }

    private byte[] loadClassData(String name) {
        String fileName = getFileName(name);
        File file = new File(path, fileName);
        InputStream inputStream = null;
        ByteArrayOutputStream arrayOutputStream = null;
        try {
            inputStream = new FileInputStream(file);
            arrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buffer)) != -1) {
                arrayOutputStream.write(buffer, 0, length);
            }
            return arrayOutputStream.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (arrayOutputStream != null) {
                try {
                    arrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private String getFileName(String name) {
        int index = name.lastIndexOf(".");
        if (index == 1) {
            return name + ".class";
        } else {
            return name.substring(index + 1) + ".class";
        }
    }
}