package com.example.nativeformmakerandroid.helper;


import android.content.Context;

import com.example.nativeformmakerandroid.model.NativeForm.LogSettings;

import java.util.Date;

/**
 * Created by mbr on 2/12/17.
 */

public class Utils {
    /**
     * @taha Compare device date to service date
     */
    public static Date CurrectDateToServerDate(Date beforeTime, Context context, String type) {
        // return beforeTime;
        LogHelper.d("CurrectDateTo", "+++");
        // int minuteDif = SharedPreferencesHelper.GetInt(context, LogSettings.PARRENT_LOG, LogSettings.TIME_DIFFERENCE_IN_MINUTES, 0);
        int minuteDif = LogSettings.get_Time_Diffrence_app_server(0);
        /**
         * I commented these Lines of code because the SharedPreferencesHelper method crashed in android 4.2.2 and android 4.3
         * */
        //      SimpleDateFormat sdf = new SimpleDateFormat(ConstHelper.Simple_Date_Format);
//          String time_bef_aft_last = SharedPreferencesHelper.GetString(context, LogSettings.PARRENT_LOG, LogSettings.TIME_BEFOR_AFTER_TIMEDIF_CORRECTION, "no before_after");

        //      String beforeFter = "\n" + UnicodeHelper.numberToEnglish(sdf.format(beforeTime));

        /**-
         10 -> app time    12 > server time   then   minuteDif = + 2   ---  real time -> 10 + (+2)
         10 -> app time    08 -> server time   then   minuteDif = - 2   ---  real time -> 10 + (-2)
         */

        if (minuteDif != 0) {
            final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs
            long curTimeInMs = beforeTime.getTime();
            Date afterAddingMins = new Date(curTimeInMs + (minuteDif * ONE_MINUTE_IN_MILLIS));

//            if (type.contentEquals(locationInsertionType)) {
//                beforeFter = time_bef_aft_last + beforeFter + "," + UnicodeHelper.numberToEnglish(sdf.format(afterAddingMins));
//                SharedPreferencesHelper.SetString(context, LogSettings.PARRENT_LOG, LogSettings.TIME_BEFOR_AFTER_TIMEDIF_CORRECTION, beforeFter);
//            }

            return afterAddingMins;
        } else {

//            if (type.contentEquals(locationInsertionType)) {
//                beforeFter = time_bef_aft_last + beforeFter + "=  minuteDif=0";
//                SharedPreferencesHelper.SetString(context, LogSettings.PARRENT_LOG, LogSettings.TIME_BEFOR_AFTER_TIMEDIF_CORRECTION, beforeFter);
//            }
            return beforeTime;

        }
    }

}
