package net.logiico.formnativeandroidjava.callback;

public interface CustomTimePickerCallback {

    /**
     * @param timeOnly     time only
     * @param dateWithTime full date with time
     */
    void onTimeSet(long timeOnly, long dateWithTime);
}
