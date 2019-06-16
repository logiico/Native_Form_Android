package com.example.nativeformmakerandroid.model.NativeForm;

/**
 * Created by imac on 10/19/17.
 */

public enum EnumDataStatusInApp {

    //   ALL(-1), // MEANS USER ADDED SOME DATA BUT NOT SAVED THEN ,JUST BACK FROM ACTIVITY, do NOT send to server

    TEMPORARY(0), // MEANS USER ADDED SOME DATA BUT NOT SAVED THEN ,JUST BACK FROM ACTIVITY, do NOT send to server
    SAVED(1), // USER SAVED DATA , do send to server
    EDITED(2), // USER EDITED , do send to server
    SENT(3), // DATA IS SENT, after sendig data to server or after recivieng data from server, do NOT send to server
    TEMPORARY_NO_LOC(4), //
    STATUS_NOT_SET_YET(5); //

    private int intValue;

    EnumDataStatusInApp(int intValue) {
        this.intValue = intValue;

    }

    public static EnumDataStatusInApp fromInt(int i) {
        for (EnumDataStatusInApp b : EnumDataStatusInApp.values()) {
            if (b.getIntValue() == (i)) {
                return b;
            }
        }
        return null;
    }

    public int getIntValue() {
        return intValue;
    }


}
