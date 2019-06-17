package com.mti.cityguide.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;



public class AppPreferences {
    

    public static String getString(Context ctx, String key, String defaultValue) {
        SharedPreferences appPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        return appPreferences.getString(key, defaultValue);
    }

    public static void setString(Context ctx, String key, String value) {
        SharedPreferences appPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        appPreferences.edit().putString(key, value).apply();
    }

    public static int getInt(Context ctx, String key, int defaultValue) {
        SharedPreferences appPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        return appPreferences.getInt(key, defaultValue);
    }

    public static void setInt(Context ctx, String key, int value) {
        SharedPreferences appPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        appPreferences.edit().putInt(key, value).apply();
    }

    public static boolean getBoolean(Context ctx, String key, boolean defaultValue) {
        SharedPreferences appPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        return appPreferences.getBoolean(key, defaultValue);
    }

    public static void setBoolean(Context ctx, String key, boolean value) {
        SharedPreferences appPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        appPreferences.edit().putBoolean(key, value).apply();
    }


}
