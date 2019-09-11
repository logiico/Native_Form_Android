package net.logiico.formnativeandroidjava.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "TemplateResult")
public class RoomTemplateResult {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "u_id")
    public String uId;

    @ColumnInfo(name = "lat")
    public String lat;

    @ColumnInfo(name = "lng")
    public String lng;

    @ColumnInfo(name = "type")
    public int type;

    @ColumnInfo(name = "data_status_app")
    public int statusInApp;

    @ColumnInfo(name = "data_status_server")
    public int statusInServer;

    @ColumnInfo(name = "data_json")
    public String dataJson;

    @ColumnInfo(name = "template_id")
    public int templateId;

    @ColumnInfo(name = "customer_name")
    public String customerName;

    @ColumnInfo(name = "customer_address")
    public String customerAddress;

    @ColumnInfo(name = "customer_phone")
    public String customerPhone;

    @ColumnInfo(name = "version")
    public int version;

    @ColumnInfo(name = "register_time")
    public String registerTime;

    @ColumnInfo(name = "last_activity_time")
    public String lastActivityTime;

    @ColumnInfo(name = "mission_id")
    public int missionId;

    @Ignore
    public List<RoomFileModel> fileModelList; // unclear: called FileModelJavaScript in model package

    public RoomTemplateResult() {
    }


    public enum EnumResultStatusInApp {

        //   ALL(-1), // MEANS USER ADDED SOME DATA BUT NOT SAVED THEN, JUST BACK FROM ACTIVITY, do NOT send to server

        TEMPORARY(0), // MEANS USER ADDED SOME DATA BUT NOT SAVED THEN, JUST BACK FROM ACTIVITY, do NOT send to server
        SAVED(1), // USER SAVED DATA , do send to server
        EDITED(2), // USER EDITED , do send to server
        SENT(3), // DATA IS SENT, after sending data to server or after receiving data from server, do NOT send to server
        TEMPORARY_NO_LOC(4), //
        NOT_SET(5); //

        private int intValue;

        EnumResultStatusInApp(int intValue) {
            this.intValue = intValue;

        }

        public static EnumResultStatusInApp fromInt(int i) {
            for (EnumResultStatusInApp b : EnumResultStatusInApp.values()) {
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

    public enum EnumResultStatusInServer {
        OPEN(1), // user can edit and send data again
        CLOSED(2),
        NO_STATUS(0); // NO STATUS

        private int intValue;

        EnumResultStatusInServer(int intValue) {
            this.intValue = intValue;

        }

        public static EnumResultStatusInServer fromInt(int i) {
            for (EnumResultStatusInServer b : EnumResultStatusInServer.values()) {
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

    public enum EnumResultType {

        MISSION(0),
        MARKETING(1);

        private int intValue;

        EnumResultType(int intValue) {
            this.intValue = intValue;

        }

        public static EnumResultType fromInt(int i) {
            for (EnumResultType b : EnumResultType.values()) {
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
}
