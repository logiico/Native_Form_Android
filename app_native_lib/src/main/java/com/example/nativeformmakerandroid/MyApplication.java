package com.example.nativeformmakerandroid;


import android.app.Application;

import android.app.ProgressDialog;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.Location;

import java.util.Calendar;


/**
 * Created by lenovo on 5/10/2016.
 */
public class MyApplication extends Application /*, LocationListener*//*, SensorEventListener, StepListener */ {


    private static MyApplication instance;
    public TenthActivity activity;

    public MyApplication() {
        instance = this;
    }


    public static MyApplication getInstance() {
        return instance;
    }


    public void setcontext(TenthActivity context) {
        activity = context;
    }

    public void onCreate() {
        super.onCreate();
        instance = this;
    }


    @Override
    public void onTerminate() {

        super.onTerminate();
    }



}
