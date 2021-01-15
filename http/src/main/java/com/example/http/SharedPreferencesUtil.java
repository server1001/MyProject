package com.example.http;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import androidx.annotation.NonNull;

/**
 * Created by xieyusheng on 2018/2/13.
 */

public class SharedPreferencesUtil {
    private final SharedPreferences sp;
    public static SharedPreferencesUtil instance;
    private static String FILE_NAME;

    public static SharedPreferencesUtil getInstance() {
        if (instance==null){
            synchronized (SharedPreferencesUtil.class){
                if (instance==null){
                    instance=new SharedPreferencesUtil(SdkForeignManager.getInstance().getContext());
                }
            }
        }
        return instance;
    }

    public static SharedPreferencesUtil newInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferencesUtil(context);
        }
        return instance;
    }

    /**
     * @param context 上下文
     */
    public SharedPreferencesUtil(@NonNull Context context) {
        FILE_NAME = context.getPackageName() + "_preferences";
        sp = context.getSharedPreferences(
                context.getPackageName() + "_preferences", Context.MODE_PRIVATE);
    }

    /**
     * @param context 上下文
     * @param file    文件名称
     * @param mode    加载模式,例如Context.MODE_PRIVATE
     */
    public SharedPreferencesUtil(@NonNull Context context, @NonNull String file,
                                 @NonNull int mode) {
        sp = context.getSharedPreferences(file, mode);
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public int getInt(final String key, final int defaultValue) {
        int resultInt;
        try {
            String result = sp.getString(key, "");
            resultInt = Integer.parseInt(AESUtil.decode(result));
        } catch (ClassCastException e) {
            try {
                // 由于之前存储的是int，需要通过getInt方法获取出来
                resultInt = sp.getInt(key, defaultValue);
                // 将获取出来的int通过加密的方式在存储一次
                putInt(key, resultInt);
            } catch (Exception e1) {
                e.printStackTrace();
                return defaultValue;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
        return resultInt;
    }

    /**
     * @param key
     * @param value
     */
    public void putInt(final String key, final int value) {
        SharedPreferences.Editor editor = sp.edit();
        // 对value 进行AES加密
        editor.putString(key, AESUtil.encode(String.valueOf(value)));
        SharedPreferencesCompat.apply(editor);;
    }

    /**
     * @param key
     * @param value
     */
    public void putInt(final String key, final Integer value) {
        if (null != value) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt(key, value);
            SharedPreferencesCompat.apply(editor);;
        }
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public long getLong(final String key, final long defaultValue) {
        long resultLong;
        try {
            String result = sp.getString(key, "");
            resultLong = Long.parseLong(AESUtil.decode(result));
        } catch (ClassCastException e) {
            try {
                // 由于之前存储的是int，需要通过getInt方法获取出来
                resultLong = sp.getLong(key, defaultValue);
                // 将获取出来的int通过加密的方式在存储一次
                putLong(key, resultLong);
            } catch (Exception e1) {
                e.printStackTrace();
                return defaultValue;
            }
        } catch (Exception e) {
            return defaultValue;
        }
        return resultLong;
    }

    /**
     * @param key
     * @param value
     */
    public void putLong(final String key, final long value) {
        SharedPreferences.Editor editor = sp.edit();
        // 对value 进行AES加密
        editor.putString(key, AESUtil.encode(String.valueOf(value)));

        SharedPreferencesCompat.apply(editor);;
    }

    /**
     * @param key
     * @param value
     */
    public void putLong(final String key, final Long value) {
        if (null != value) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putLong(key, value);
            SharedPreferencesCompat.apply(editor);;
        }
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public float getFloat(final String key, final float defaultValue) {
        float resultFloat;
        try {
            String result = sp.getString(key, "");
            resultFloat = Float.parseFloat(AESUtil.decode(result));
        } catch (ClassCastException e) {
            try {
                // 由于之前存储的是int，需要通过getInt方法获取出来
                resultFloat = sp.getFloat(key, defaultValue);
                // 将获取出来的int通过加密的方式在存储一次
                putFloat(key, resultFloat);
            } catch (Exception e1) {
                e.printStackTrace();
                return defaultValue;
            }
        } catch (Exception e) {
            return defaultValue;
        }
        return resultFloat;
    }

    /**
     * @param key
     * @param value
     */
    public void putFloat(final String key, final float value) {
        SharedPreferences.Editor editor = sp.edit();
        // 对value 进行AES加密
        editor.putString(key, AESUtil.encode(String.valueOf(value)));
        SharedPreferencesCompat.apply(editor);;
    }

    /**
     * @param key
     * @param value
     */
    public void putFloat(final String key, final Float value) {
        if (null != value) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putFloat(key, value);
            SharedPreferencesCompat.apply(editor);;
        }
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public String getString(final String key, final String defaultValue) {
        String result = sp.getString(key, "");
        String resultString;
        // 如果不存在该key，直接返回defaultValue
        if (!sp.contains(key)) {
            return defaultValue;
        }
        // 其他按照没有加密过的处理
        try {
            resultString = AESUtil.decode(result);
        } catch (Exception e) {
            putString(key, result);
            return result;
        }
        return resultString;
    }

    /**
     * @param key
     * @param value
     */
    public void putString(final String key, final String value) {
        SharedPreferences.Editor editor = sp.edit();
        // 对value 进行AES加密
        editor.putString(key, AESUtil.encode(String.valueOf(value)));
        SharedPreferencesCompat.apply(editor);
    }


    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public boolean getBoolean(final String key, final boolean defaultValue) {
        boolean resultBolean;
        try {
            String result = sp.getString(key, "");
            resultBolean = Boolean.getBoolean(AESUtil.decode(result));
        } catch (ClassCastException e) {
            try {
                // 由于之前存储的是int，需要通过getInt方法获取出来
                resultBolean = sp.getBoolean(key, defaultValue);
                // 将获取出来的int通过加密的方式在存储一次
                putBoolean(key, resultBolean);
            } catch (Exception e1) {
                e.printStackTrace();
                return defaultValue;
            }
        } catch (Exception e) {
            return defaultValue;
        }
        return resultBolean;
    }

    /**
     * @param key
     * @param value
     */
    public void putBoolean(final String key, final boolean value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * @param key
     * @param value
     */
    public void putBoolean(final String key, final Boolean value) {
        if (null != value) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean(key, value);
            SharedPreferencesCompat.apply(editor);
        }
    }

    /**
     * 删除对应的键值对
     *
     * @param key 待删除的key
     */
    public void remove(String key) {
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     */
    public void clear() {
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();

        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     */
    public boolean contains(String key) {
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     */
    public Map<String, ?> getAll() {
        return sp.getAll();
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param context
     * @param key
     */
    public static void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 存放实体类以及任意类型
     *
     * @param context 上下文对象
     * @param key
     * @param obj
     */
    public void putObject(String key, Object obj) {
        if (obj instanceof Serializable) {// obj必须实现Serializable接口，否则会出问题
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(obj);
                String string64 = new String(Base64.encode(baos.toByteArray(), 0));
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(key, string64).apply();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            throw new IllegalArgumentException("the obj must implement Serializble");
        }

    }

    public Object getObject(String key) {
        Object obj = null;
        try {
            String base64 = sp.getString(key, "");
            if (base64.equals("")) {
                return null;
            }
            byte[] base64Bytes = Base64.decode(base64.getBytes(), 1);
            ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            obj = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     *
     * @author zhy
     */
    private static class SharedPreferencesCompat {
        private static final Method APPLY_METHOD = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (APPLY_METHOD != null) {
                    APPLY_METHOD.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
            editor.commit();
        }
    }
}
