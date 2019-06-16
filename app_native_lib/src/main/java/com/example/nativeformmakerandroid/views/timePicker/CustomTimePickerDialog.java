package com.example.nativeformmakerandroid.views.timePicker;

import android.content.Context;

import com.example.nativeformmakerandroid.Interfaces.Callback_FN_TimePicker_Listener;
import com.example.nativeformmakerandroid.R;
import com.example.nativeformmakerandroid.ActivityFormNative;

public class CustomTimePickerDialog implements Callback_FN_TimePicker_Listener {
    private Callback_FN_TimePicker_Listener myCallback;


    @Override
    public void onCallBack(long timeOnly) {
        this.myCallback.onCallBack(timeOnly);
    }


    public void setTimeClickedListener(Callback_FN_TimePicker_Listener callback) {
        this.myCallback = callback;
    }


    public CustomTimePickerDialog(Context ctx, int currentHour, int currentMinute) {
        CustomTimePickerFragmentDialog mTimePickerDialog = CustomTimePickerFragmentDialog.newInstance(CustomDateTimeBuilder.get()
                .withTime(true)
                .with24Hours(true)
                .withCurrentHour(currentHour)
                .withCurrentMinute(currentMinute)
                .withTheme(R.style.pickersTheme));


        mTimePickerDialog.show(((ActivityFormNative) ctx).getSupportFragmentManager(), "TimePickerFragmentDialog");

    }

}