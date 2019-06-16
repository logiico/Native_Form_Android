package com.example.nativeformmakerandroid;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import com.example.nativeformmakerandroid.helper.LogHelper;


import java.util.List;



/**
 * Created by mbr on 7/9/16.
 */

/**
 * Every Activity in this project needs to implement TenthActivity.
 *
 * @author Taha Gh.S
 * @version 0.1
 * @since 26 aug 2017
 */
public abstract class TenthActivity extends AppCompatActivity {


    public abstract int getLayoutResourceID();


    @Override
    protected void onStop() {

        super.onStop();
//        MyApplication.activityStopped();

        //isActive = false;
    }

    @Override
    protected void onStart() {
        // SharedPreferencesHelper.SetBoolean(MyApplication.getInstance(), prefParent, pref_AppForeGroundStatus, true);
        LogHelper.d("TAG_STAY_location", "onStart -- pref_AppForeGroundStatus >> true >>>> : >>>>");
        super.onStart();

    }

    private boolean isForgroundAndroidand6Above(Context mContext) {
        boolean foreground = false;
        LogHelper.d("TAG_STAY_location", " ----------------- android 6 and above");

        ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            foreground = false;
        }
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                LogHelper.d("TAG_STAY_location-", appProcess.processName);
                if (mContext.getPackageName().equalsIgnoreCase(appProcess.processName)) {
                    LogHelper.d("TAG_STAY_location--", "foreground true:" + appProcess.processName);
                    foreground = true;
                }
            }
        }
        LogHelper.d("TAG_STAY_location", "foreground value:" + foreground);
        return foreground;
    }

    private boolean isAppOnForeground_KitkatandLower(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        final String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // MyApplication.getInstance().setcontext(this);

        setContentView(getLayoutResourceID());
        setUpElements();
        setUpOnClicks();
        loadData();
    }

    public abstract String getName();

    @Override
    protected void onRestart() {
        super.onRestart();
        // MyApplication.getInstance().setcontext(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onResume() {
//        super.onResume();
        MyApplication.getInstance().setcontext(this);


        super.onResume();
    }

    /**
     * write {{@link #findViewById(int)}} for views in this method
     */
    public abstract void setUpElements();

    /**
     * fill your views with your data in this method
     */
    public abstract void setUpElementsWithData();

    /**
     * implement {@link android.view.View.OnClickListener} in this method
     */
    public abstract void setUpOnClicks();

    /**
     * load your data from local db or service and etc
     */
    public abstract void loadData();


}
