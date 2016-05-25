package com.bcx.managersystem.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 白杨 on 2016/5/5.
 */
public class PreferenceUtil {
    private final static String SP_NAME = "ArrowImDemoConfig";
    private static SharedPreferences sp;

    // private static Context context;

    private static SharedPreferences getSp(Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return sp;
    }

    /**
     * 保存布尔值
     *
     * @param context
     * @param key
     * @param value
     * @return
     */
    public static boolean saveBoolean(Context context, String key, boolean value) {
        return getSp(context).edit().putBoolean(key, value).commit();
    }

    /**
     * 保存字符串
     *
     * @param context
     * @param key
     * @param value
     * @return
     */
    public static boolean saveString(Context context, String key, String value) {
        return getSp(context).edit().putString(key, value).commit();
    }

    /**
     * 保存long型
     *
     * @param context
     * @param key
     * @param value
     * @return
     */
    public static boolean saveLong(Context context, String key, long value) {
        return getSp(context).edit().putLong(key, value).commit();
    }

    /**
     * 保存int型
     *
     * @param context
     * @param key
     * @param value
     * @return
     */
    public static boolean saveInt(Context context, String key, int value) {
        return getSp(context).edit().putInt(key, value).commit();
    }

    /**
     * 保存short型
     *
     * @param context
     * @param key
     * @param value
     * @return
     */
    public static boolean saveShort(Context context, String key, short value) {
        return getSp(context).edit().putInt(key, value).commit();
    }

    /**
     * 保存float型
     *
     * @param context
     * @param key
     * @param value
     */
    public static boolean saveFloat(Context context, String key, float value) {
        return getSp(context).edit().putFloat(key, value).commit();
    }

    /**
     * 获取字符值
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static String getString(Context context, String key, String defValue) {
        return getSp(context).getString(key, defValue);
    }

    /**
     * 获取int值
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static int getInt(Context context, String key, int defValue) {
        return getSp(context).getInt(key, defValue);
    }

    /**
     * 获取short值
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static short getShort(Context context, String key, short defValue) {
        return (short) getSp(context).getInt(key, defValue);
    }

    /**
     * 获取long值
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static long getLong(Context context, String key, long defValue) {
        return getSp(context).getLong(key, defValue);
    }

    /**
     * 获取float值
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static float getFloat(Context context, String key, float defValue) {
        return getSp(context).getFloat(key, defValue);
    }

    /**
     * 获取布尔值
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getBoolean(Context context, String key,
                                     boolean defValue) {
        return getSp(context).getBoolean(key, defValue);
    }

    /** 清除该Key数据 */
    public static boolean remove(Context context, String key) {
        return getSp(context).edit().remove(key).commit();
    }

    /** 清除所有SharePre保存的数据 */
    public static boolean clearAllData(Context context) {
        return getSp(context).edit().clear().commit();
    }

    // 以下注释的代码用到的包为 commons-codec-1.3
    /**
     * 将对象进行base64编码后保存到SharePref中
     *
     * @param context
     * @param key
     * @param object
     */
    // public static void saveObj(Context context, String key, Object object) {
    // if (sp == null)
    // sp = context.getSharedPreferences(SP_NAME, 0);
    //
    // ByteArrayOutputStream baos = new ByteArrayOutputStream();
    // ObjectOutputStream oos = null;
    // try {
    // oos = new ObjectOutputStream(baos);
    // oos.writeObject(object);
    // // 将对象的转为base64码
    // String objBase64 = new String(Base64.encodeBase64(baos
    // .toByteArray()));
    //
    // sp.edit().putString(key, objBase64).commit();
    // oos.close();
    // } catch (IOException e) {
    // LogUtil.e(tag, "========= 对象保存到SharePref时出错 ============");
    // e.printStackTrace();
    // }
    // }

    /**
     * 将SharePref中经过base64编码的对象读取出来
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    // public static Object getObj(Context context, String key) {
    // if (sp == null)
    // sp = context.getSharedPreferences(SP_NAME, 0);
    // String objBase64 = sp.getString(key, null);
    // if (StringUtils.isEmpty(objBase64))
    // return null;
    //
    // // 对Base64格式的字符串进行解码
    // byte[] base64Bytes = Base64.decodeBase64(objBase64.getBytes());
    // ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
    //
    // ObjectInputStream ois;
    // Object obj = null;
    // try {
    // ois = new ObjectInputStream(bais);
    // obj = ois.readObject();
    // ois.close();
    // } catch (Exception e) {
    // e.printStackTrace();
    // LogUtil.e(tag, "========= 从SharePref读取对象时出错 ============");
    // }
    // return obj;
    // }

}