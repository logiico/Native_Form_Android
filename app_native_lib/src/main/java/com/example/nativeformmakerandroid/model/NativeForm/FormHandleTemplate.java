package com.example.nativeformmakerandroid.model.NativeForm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.os.AsyncTask;

import com.example.nativeformmakerandroid.R;
import com.example.nativeformmakerandroid.helper.LogHelper;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import com.example.nativeformmakerandroid.MyApplication;

/**
 * Created by imac on 10/16/17.
 */

/**
 * Get Templates And Use Them for Form:
 * data : every where in this doc means the data that user filled in templates/forms.
 * <p>
 * > Note : the data are saved/Inserted to DB when it is the first time (using fab), if data already existed it will be updated.
 * <p>
 * > Note : data has status_in_app in 4 status (saved,edited,temporary,sent)
 * ...
 *
 * @author Taha Gh.S
 * @version 0.1
 * @since October 2017
 */

public class FormHandleTemplate {
    public static final String TEMPLATE_TABLE = "Templates";
    public static final String TEMPLATE_ID = "id";
    public static final String TEMPLATE_NAME = "name";
    public static final String TEMPLATE_LINK = "link";
    public static final String TEMPLATE_TYPE = "type";
    public static final String TEMPLATE_CONTENT = "template_content";
    public static final String TEMPLATE_STATUS = "status";
    public static final String VERSION = "Version";
    public static final String TEMPLATE_SHAREDPREF = "TEMPLATE_SHAREDPREF";
    public static final String TEMPLATE_TIME_SHAREDPREF = "TEMPLATE_TIME_SHAREDPREF";
    public static final String ALL_TEMPLATES_HAS_BEEN_DOWNLOADED = "ALL_TEMPLATES_HAS_BEEN_DOWNLOADED";
    public static final String ALL_TEMPLATES_HAS_BEEN_DOWNLOADED_JSON = "ALL_TEMPLATES_HAS_BEEN_DOWNLOADED_JSON";
    public static String TAG = "TEMPLATE >>";
    public static Context contextOfPage;
    public int id;
    public String name;
    public String link;
    public int type;
    public String template_content;
    public int status;
    public int Version;



    /**
     * if new Version which comes from server means it should be downloaded again
     */


//    public static String getTemplatesContentById(Context context, int id) {
//        Cursor cur = null;
//        try {
//
//            String col[] = {TEMPLATE_CONTENT};
//            cur = MyApplication.dbApp.dataBase.query(TEMPLATE_TABLE, col, TEMPLATE_ID + "=" + id, null, null, null, /*TEMPLATE_ID +*/ null /*"  ASC "*/, null /*String.valueOf(n)*/);
//            Integer num = cur.getCount();
//            // Log.d("template>>count", String.valueOf(num));
//            FormHandleTemplate formHandleTemplate = new FormHandleTemplate();
//          //  ArrayList<FormHandleTemplate> formHandleTemplates = new ArrayList<FormHandleTemplate>();
//            if (cur != null) {
//                cur.moveToFirst();
//                int content_index = cur.getColumnIndex(TEMPLATE_CONTENT);
//                formHandleTemplate.template_content = cur.getString(content_index);
//
//            } else {
//                cur.close();
//                return null;
//            }
//            cur.close();
//            return formHandleTemplate.template_content;
//
//        } catch (Exception e) {
//            cur.close();
//            LogHelper.e("Exception : ", "TEMPLATE : TEMPLATE");
//            return null;
//
//        }
//    }

        public static String getTemplatesContent (Context context,int id){

            String jsontext;
            try {


               // InputStream is = context.getResources().openRawResource(R.raw.form_promotion_kaleh);
                InputStream is = context.getResources().openRawResource(id);

                byte[] buffer = new byte[is.available()];
                while (is.read(buffer) != -1) ;
                jsontext = new String(buffer);


            } catch (Exception e) {
                jsontext = null;
                LogHelper.e(TAG, "" + e.toString());
            }

            return jsontext;


        }

    }

