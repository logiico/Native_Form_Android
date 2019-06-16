package com.example.nativeformmakerandroid.helper;


import android.app.ProgressDialog;
import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.widget.Toast;

import com.example.nativeformmakerandroid.views.CustomTypefaceSpan;


public class ToastHelper {
    public static void Show(String message, Context context) {

        SpannableStringBuilder ss1 = new SpannableStringBuilder(message);
        ss1.setSpan(new CustomTypefaceSpan("", FontHelper.get(context,0 /*ConstHelper.DEFAULT_FONT*/)), 0, ss1.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        Toast.makeText(context, ss1, Toast.LENGTH_LONG).show();
    }

    public static ProgressDialog CreateDialog(String message, Context context) {
        ProgressDialog pd = new ProgressDialog(context);
        pd.setCanceledOnTouchOutside(false);
        SpannableStringBuilder ss1 = new SpannableStringBuilder(message);
        ss1.setSpan(new CustomTypefaceSpan("", FontHelper.get(context,0 /*ConstHelper.DEFAULT_FONT*/)), 0, ss1.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        pd.setMessage(ss1);

        return pd;
    }


}
