package net.logiico.formnativeandroidjava.helper;

import android.content.Context;

import java.util.Date;

public class Utils {
    public static Date CurrectDateToServerDate(Date beforeTime, Context context, String type) {
        LogHelper.d("CurrectDateTo", "+++");
        int minuteDif = 0;

        if (minuteDif != 0) {
            final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs
            long curTimeInMs = beforeTime.getTime();
            Date afterAddingMins = new Date(curTimeInMs + (minuteDif * ONE_MINUTE_IN_MILLIS));


            return afterAddingMins;
        } else {

            return beforeTime;

        }
    }
}
