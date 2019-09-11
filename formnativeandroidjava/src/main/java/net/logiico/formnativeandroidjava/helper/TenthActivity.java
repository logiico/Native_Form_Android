package net.logiico.formnativeandroidjava.helper;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import net.logiico.formnativeandroidjava.BuildConfig;
import net.logiico.formnativeandroidjava.MyApplication;

import java.util.List;

public abstract class TenthActivity extends AppCompatActivity {
    public static String prefParent = "appStatusParrent";
    public static String pref_AppForeGroundStatus = "pref_AppForeGroundStatus";

    public DrawerLayout drawerLayout;


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

    //    @Override
//    protected void onPause() {
//
//        TimerTask backgroundCheck = new TimerTask() {
//
//            @Override
//            public void run() {
//
//                boolean status;
//                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
//                    status = isForgroundAndroidand6Above(getApplicationContext());
//                } else {
//                    status = isAppOnForeground_KitkatandLower(getApplicationContext());
//                }
//                if (status) {
//                    SharedPreferencesHelper.SetBoolean(MyApplication.getInstance(), prefParent, pref_AppForeGroundStatus, true);
//                    LogHelper.d("TAG_STAY_location", "onPause -- pref_AppForeGroundStatus >> True");
//                } else {
//                    SharedPreferencesHelper.SetBoolean(MyApplication.getInstance(), prefParent, pref_AppForeGroundStatus, false);
//                    LogHelper.d("TAG_STAY_location", "onPause -- pref_AppForeGroundStatus >> false");
//                }
//                // APP in foreground, do something else
//            }
//        };
//
//        Timer isBackgroundChecker = new Timer();
//        isBackgroundChecker.schedule(backgroundCheck, 1000);
//        super.onPause();
//    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().getAppComponent().inject(this);
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


    /***
     *  in android 6 and above the service will needs a
     permission of getting location , it started at first
     but without permission then when the permission is given
     the LogService cant be run agian, so i put it here
     (for android 6 ) to start LogService. when user logs in and permission is given
     then the service going to start for the first time
     */
    @Override
    protected void onResume() {
//        super.onResume();
        MyApplication.getInstance().setcontext(this);
        //  Log.e("act_tenth_TAG", "onResume of :" + this.getClass().getSimpleName().toString());


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
