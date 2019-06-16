package com.example.nativeformmakerandroid.model.NativeForm;

/**
 * Created by imac on 8/19/17.
 */

public class LocDbModel {
    public static final String LOCATIONS_TABLE = "Locations";

    public static final String DEVICE_ID = "DeviceId";
    public static final String LATITUTE = "Lat";
    public static final String LONGTITUTE = "Lng";
    public static final String UID = "Uid";
    public static final String ACCURACY = "Accuracy";
    public static final String ENTERY_DATE = "Entry_Date";
    public static final String STAY_TIME = "Stay_Time";
    public static final String SPEED = "Speed";

    public String DeviceId;
    public String Lat;
    public String Lng;
    public String Uid;
    public String Accuracy;
    public String Entry_Date;
    public int Stay_Time;
    public int Speed;

}
/*
* {
  "model": [
    {
      "DeviceId": 0,
      "Lat": 0,
      "Lng": 0,
      "Uid": "string",
      "Accuracy": "string",
      "Entry_Date": "2017-08-19T05:19:37.439Z"
    }
  ]
}
* */