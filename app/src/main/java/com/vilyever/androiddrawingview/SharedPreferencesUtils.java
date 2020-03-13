package com.vilyever.androiddrawingview;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @Copyright: 2015 Beijing Safety Test Technology Co.,Ltd.
 * @time: 2015-10-15 下午2:04:23
 * @author: F.P.Y
 * @version: v1.0
 * @description: 缓存数据工具类
 * @Historical version:
 * 
 **/
public class SharedPreferencesUtils {

	/** sp's name */
	public static final String SP_NAME = "config";

	private static SharedPreferences sp;

	/**
	 * @param context
	 * @param key
	 * @param value
	 *            保存和获取字符串
	 */
	public static void saveString(Context context, String key, String value) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		sp.edit().putString(key, value).commit();
	}

	public static String getString(Context context, String key, String defValue) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		return sp.getString(key, defValue);
	}

	/**
	 * @param context
	 * @param key
	 * @param value
	 *            保存和获取boolean值
	 */
	public static void saveBoolean(Context context, String key, boolean value) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		sp.edit().putBoolean(key, value).commit();
	}

	public static boolean getBoolean(Context context, String key,
			boolean defValue) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		return sp.getBoolean(key, defValue);
	}

	/**
	 * @param context
	 * @param key
	 * @param value
	 *            保存和获取int值
	 */
	public static void saveInt(Context context, String key, int value) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		sp.edit().putInt(key, value).commit();
	}

	public static int getInt(Context context, String key, int defValue) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		return sp.getInt(key, defValue);
	}

}
