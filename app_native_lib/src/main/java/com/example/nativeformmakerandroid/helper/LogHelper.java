package com.example.nativeformmakerandroid.helper;

import android.util.Log;

import com.example.nativeformmakerandroid.BuildConfig;


/**
 * Created by imac on 4/29/18.
 */


public class LogHelper {

    public static final String Parrent = "LogHelper_Parrent";
    public static final String LogHelper_SHOW_LOG_OR_NOT = "LogHelper_SHOW_LOG_OR_NOT";

    public static void d(String Tag, String message) {
        // SharedPreferencesHelper.GetBoolean(MyApplication);
        if (BuildConfig.DEBUG ) {
            if (message != null)
                Log.d(Tag, message);
            else Log.d(Tag, " ::>  " + "Null it is -");

        }
    }

    public static void e(String Tag, String message) {
        if (BuildConfig.DEBUG ) {
            if (message != null)
                Log.e(Tag, message);
            else Log.e(Tag, " ::>  " + "Null it is -");

        }
    }

    public static void i(String Tag, String message) {
        if (BuildConfig.DEBUG ) {
            if (message != null)
                Log.i(Tag, message);
            else Log.i(Tag, " ::>  " + "Null it is -");

        }
    }

    public static void v(String Tag, String message) {
        if (BuildConfig.DEBUG ) {
            if (message != null)
                Log.v(Tag, message);
            else Log.v(Tag, " ::>  " + "Null it is -");

        }
    }

    public static void wtf(String Tag, String message) {
        if (BuildConfig.DEBUG) {
            if (message != null)
                Log.wtf(Tag, message);
            else Log.wtf(Tag, " ::>  " + "Null it is -");

        }
    }


}
