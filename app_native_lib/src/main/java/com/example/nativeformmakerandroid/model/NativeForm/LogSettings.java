package com.example.nativeformmakerandroid.model.NativeForm;


/**
 * Created by imac on 8/23/17.
 */

public class LogSettings {

    public static int get_Time_Diffrence_app_server(int defaulttimeDif) {
        return 0;

        /**
         * here we diabled this feature by returning 0 which means there bever will be any time diffrence between server and app , because of foreign users had trouble with this feature.
         *
         * but to enable it just use this line of code
         *
         * return SharedPreferencesHelper.GetInt(MyApplication.getInstance(), LogSettings.PARRENT_LOG, LogSettings.TIME_DIFFERENCE_IN_MINUTES, defaulttimeDif);
         * */
    }

}
