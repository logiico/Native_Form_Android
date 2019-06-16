package com.example.nativeformmakerandroid.helper;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

public class DeviceHelper {
    public static String GetDeviceID(Context context) {
        try {

            if (SharedPreferencesHelper.GetString(context, "SYSTEM", "DEVICE_ID", null) == null) {
                String device_id = Settings.Secure.getString(context.getContentResolver(),
                        Settings.Secure.ANDROID_ID);
                SharedPreferencesHelper.SetString(context, "SYSTEM", "DEVICE_ID", device_id);
            }

            return SharedPreferencesHelper.GetString(context, "SYSTEM", "DEVICE_ID", null);

        } catch (Exception e) {
            return null;
        }
    }

    public static String GetDeviceModel() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    public static String GetOsVersion() {
        return Build.VERSION.RELEASE;
    }

    public static String GetOsName() {
        return "Android";
    }

    static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

    public static Rect GetScreenSize(Context context) {
        DisplayMetrics display = context.getResources().getDisplayMetrics();

        int orientation = 1;

        return new Rect(0, 0,
                orientation == Configuration.ORIENTATION_PORTRAIT ? display.widthPixels : display.heightPixels,
                orientation == Configuration.ORIENTATION_PORTRAIT ? display.heightPixels : display.widthPixels);
    }

    public static boolean isTablet(AppCompatActivity context) {
        int HEIGHT = context.getWindowManager().getDefaultDisplay().getHeight();
        int WIDTH = context.getWindowManager().getDefaultDisplay().getWidth();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            if ((context.getResources().getConfiguration().screenLayout &
                    Configuration.SCREENLAYOUT_SIZE_MASK) ==
                    Configuration.SCREENLAYOUT_SIZE_XLARGE)
                return true;

        return false;
    }
}
