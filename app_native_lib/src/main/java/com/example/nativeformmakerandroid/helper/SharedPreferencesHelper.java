package com.example.nativeformmakerandroid.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {

    public static String GetString(Context context, String parent, String var, String default_value) {
        SharedPreferences shared = context.getSharedPreferences(parent, Context.MODE_PRIVATE);
        return shared.getString(var, default_value);
    }

    public static void SetString(Context context, String parent, String var, String value) {
        SharedPreferences shared = context.getSharedPreferences(parent, Context.MODE_PRIVATE);
        shared.edit().putString(var, value).commit();
    }

    public static int GetInt(Context context, String parent, String var, int default_value) {
        SharedPreferences shared = context.getSharedPreferences(parent, Context.MODE_PRIVATE);
        return shared.getInt(var, default_value);
    }

    public static void SetInt(Context context, String parent, String var, int value) {
        SharedPreferences shared = context.getSharedPreferences(parent, Context.MODE_PRIVATE);
        shared.edit().putInt(var, value).commit();
    }

    public static boolean GetBoolean(Context context, String parent, String var, boolean default_value) {
        SharedPreferences shared = context.getSharedPreferences(parent, Context.MODE_PRIVATE);
        return shared.getBoolean(var, default_value);
    }

    public static void SetBoolean(Context context, String parent, String var, boolean value) {
        SharedPreferences shared = context.getSharedPreferences(parent, Context.MODE_PRIVATE);
        shared.edit().putBoolean(var, value).commit();
    }

    public static void ClearParent(Context context, String parent) {
        SharedPreferences shared = context.getSharedPreferences(parent, Context.MODE_PRIVATE);
        shared.edit().clear().commit();
    }
}
