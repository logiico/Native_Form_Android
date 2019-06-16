package com.example.nativeformmakerandroid.model.NativeForm;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import com.example.nativeformmakerandroid.MyApplication;
import com.example.nativeformmakerandroid.helper.LogHelper;


/**
 * Created by Mohammad MBR on 11/4/2017.
 */

public class FileModel {
    public static final String sTable = "FileModel";
    public static final String TAG = "dataSentRes FileMoTAG";
    public static final String TAG_valid = "dataSentRes ٰValid";

    public static final String sUploadPercentage = "upload_percent";
    public static final String sMultiple = "multiple";
    public static final String sTitle = "title";
    private static final String sUid = "uid";
    private static final String sInputName = "input_name";
    private static final String sFilePath = "file_path";
    private static final String sStatus = "status";
    public String Uid;
    public String InputName;
    public String filePath;
    public int status;
    public int upload_percent;
    public String Title;
    public boolean Multiple;
    public enum FileStatus {
        SENT(1), SAVED(2), TEMPORARY(3), NOT_SUCCESSFUL(4);
        private int intValue;

        FileStatus(int intValue) {
            this.intValue = intValue;

        }

        public static FileStatus fromInt(int i) {
            for (FileStatus b : FileStatus.values()) {
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
