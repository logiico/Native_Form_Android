package com.example.nativeformmakerandroid.views.timePicker;

public interface CustomTimePickerCallback {

    /**
     * @param timeOnly
     *         time only
     * @param dateWithTime
     *         full date with time
     */
    void onTimeSet(long timeOnly, long dateWithTime);
}
