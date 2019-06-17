package com.example.test3_native_form;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.nativeformmakerandroid.ActivityFormNative;
import com.example.nativeformmakerandroid.MyApplication;
import com.example.nativeformmakerandroid.helper.ConstHelper;
import com.example.nativeformmakerandroid.helper.ConstString;
import com.example.nativeformmakerandroid.helper.LogHelper;
import com.example.nativeformmakerandroid.helper.Utils;
import com.example.nativeformmakerandroid.model.NativeForm.EnumDataStatusInApp;
import com.example.nativeformmakerandroid.model.NativeForm.FileModel;
import com.example.nativeformmakerandroid.model.NativeForm.FileModelJavaScript;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {
 // a----0
    private View btn;
    private static final int FORM_REQUEST_CODE = 294;
    private static final String TAG = "TAG_NATIVE_FORM";
 // abc
    /**
     * to read form in json format from raw folder
     * */
    public static String getTemplatesContent (Context context, int id){
        String jsontext;
        try { InputStream is = context.getResources().openRawResource(id);
            byte[] buffer = new byte[is.available()];
            while (is.read(buffer) != -1) ;
            jsontext = new String(buffer);
        } catch (Exception e) {
            jsontext = null;  LogHelper.e(TAG, "" + e.toString());
        }
        return jsontext;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        /**open ActivityNativeForm.java and send required data by Intenف * */
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(getApplicationContext(), ActivityFormNative.class);
                intent.putExtra(ConstString.JOB_TEMPLATE_ID, 1 /*mission.Templates.get(position).Id*/); // todo : make pop up
                long uid = Utils.CurrectDateToServerDate(new Date(), getApplicationContext(), "").getTime() /*+ "" + String.valueOf(AuthenticateHelper.getDeviceId(getApplicationContext(), ""))*/;
                intent.putExtra(ConstString.JOB_DATA_U_ID, uid); //
                intent.putExtra(ConstString.FORM_EDITABLE, true); //
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ConstHelper.Simple_Date_Format);
                String currDate = simpleDateFormat.format(Utils.CurrectDateToServerDate(new Date(), getApplicationContext(), ""));
                intent.putExtra(ConstString.FORM_ENTERING_TIME, currDate); //
                String templateJson = getTemplatesContent(MyApplication.getInstance(), R.raw.form_gs);
                intent.putExtra(ConstString.FORM_TEMPLATE_IN_JSON_FORMAT, templateJson); //
                intent.putExtra(ConstString.STATUS_IN_APP, EnumDataStatusInApp.STATUS_NOT_SET_YET.getIntValue()); //
                startActivityForResult(intent, FORM_REQUEST_CODE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogHelper.e(TAG, "1");
        LogHelper.e(TAG, "1 request code :" + requestCode);
        LogHelper.e(TAG, "1 result  code :" + resultCode);
        String data_json = "no_data_json";
        if (requestCode == FORM_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            if (data.getBooleanExtra(ConstString.JOB_TEMPLATE_SAVED_SUBMITTED, false)) {
                LogHelper.e(TAG, "a");

                data_json = data.getStringExtra(ConstString.JOB_TEMPLATE_SAVED_DATA);
            }

            LogHelper.e(TAG, "data: " + data_json);


            List<FileModelJavaScript> fileModelJavaScripts = data.getParcelableArrayListExtra(ConstString.JOB_TEMPLATE_SAVED_FILE_LIST);
            if (fileModelJavaScripts != null && fileModelJavaScripts.size() != 0){
                LogHelper.e("javascriptmbr h>> ", "size :" + fileModelJavaScripts.size());

                for (FileModelJavaScript fileModelJavaScript : fileModelJavaScripts) {
                    fileModelJavaScript.status = FileModel.FileStatus.TEMPORARY.getIntValue();
                    LogHelper.e(TAG+" InputNa", fileModelJavaScript.InputName);
                    LogHelper.e(TAG+" Title  ", fileModelJavaScript.Title);
                    LogHelper.e(TAG+" status ", String.valueOf(fileModelJavaScript.status));

                }}else {
                LogHelper.e(TAG, "file list is NULL or ZERO");

            }

        } else {
            LogHelper.e(TAG , "no result");

        }
        LogHelper.e(TAG, "11");

    }
}

