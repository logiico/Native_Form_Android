package net.logiico.formnativeandroidjava.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.logiico.formnativeandroidjava.R;
import net.logiico.formnativeandroidjava.activity.FormNativeActivity;
import net.logiico.formnativeandroidjava.callback.TimePickerCallback;
import net.logiico.formnativeandroidjava.helper.CustomDateTimeBuilder;

public class CustomTimePickerDialog implements TimePickerCallback {
    private TimePickerCallback myCallback;


    public CustomTimePickerDialog(Context ctx, int currentHour, int currentMinute) {
        CustomTimePickerFragmentDialog mTimePickerDialog = CustomTimePickerFragmentDialog.newInstance(CustomDateTimeBuilder.get()
                .withTime(true)
                .with24Hours(true)
                .withCurrentHour(currentHour)
                .withCurrentMinute(currentMinute)
                .withTheme(R.style.pickersTheme));


        mTimePickerDialog.show(((FormNativeActivity) ctx).getSupportFragmentManager(), "TimePickerFragmentDialog");

    }

    @Override
    public void onCallBack(long timeOnly) {
        this.myCallback.onCallBack(timeOnly);
    }

    public void setTimeClickedListener(TimePickerCallback callback) {
        this.myCallback = callback;
    }

}