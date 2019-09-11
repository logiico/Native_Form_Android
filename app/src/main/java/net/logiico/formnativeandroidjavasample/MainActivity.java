package net.logiico.formnativeandroidjavasample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import net.logiico.formnativeandroidjava.MyApplication;
import net.logiico.formnativeandroidjava.activity.FormNativeActivity;
import net.logiico.formnativeandroidjava.helper.ConstHelper;
import net.logiico.formnativeandroidjava.helper.LogHelper;
import net.logiico.formnativeandroidjava.helper.Utils;
import net.logiico.formnativeandroidjava.model.RoomTemplateResult;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final int FORM_REQUEST_CODE = 294;
    private static final String TAG = "TAG_NATIVE_FORM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.activity_main_test_button).setOnClickListener(view -> {
            Intent intent;
            intent = new Intent(getApplicationContext(), FormNativeActivity.class);
//            intent.putExtra(ConstString.JOB_TEMPLATE_ID, 1 /*mission.Templates.get(position).Id*/); // todo : make pop up
            long uid = Utils.CurrectDateToServerDate(new Date(), getApplicationContext(), "").getTime() /*+ "" + String.valueOf(AuthenticateHelper.getDeviceId(getApplicationContext(), ""))*/;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ConstHelper.Simple_Date_Format);
            String currDate = simpleDateFormat.format(Utils.CurrectDateToServerDate(new Date(), getApplicationContext(), ""));
            intent.putExtra(FormNativeActivity.RESULT_ENTERING_TIME, currDate); //
            String templateJson = getTemplatesContent(MyApplication.getInstance(), R.raw.form);
            intent.putExtra(FormNativeActivity.TEMPLATE_CONTENT, templateJson); //
            intent.putExtra(FormNativeActivity.TEMPLATE_EDITABLE, true); //
            intent.putExtra(FormNativeActivity.RESULT_UID, Utils.CurrectDateToServerDate(new Date(), getApplicationContext(), "").getTime());
            intent.putExtra(FormNativeActivity.RESULT_STATUS_IN_APP, RoomTemplateResult.EnumResultStatusInApp.NOT_SET.getIntValue()); //
            startActivityForResult(intent, FORM_REQUEST_CODE);
        });
    }
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
}
