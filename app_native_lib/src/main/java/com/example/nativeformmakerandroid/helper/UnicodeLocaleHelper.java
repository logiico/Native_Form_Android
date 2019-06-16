package com.example.nativeformmakerandroid.helper;


import android.content.Context;

import com.example.nativeformmakerandroid.model.NativeForm.LanguageSettings;

public class UnicodeLocaleHelper {

    public static String numberToWhatEverLocaleIs(String str, Context ctx) {
        String language = SharedPreferencesHelper.GetString(ctx, LanguageSettings.PARRENT_LOG, LanguageSettings.lANGUAGE_VALUE, LanguageSettings.FARSI_FA);

        if (language.contentEquals(LanguageSettings.ENGLISH_EN)) {

            return str.replace('۰', '0')
                    .replace('۱', '1')
                    .replace('۲', '2')
                    .replace('۳', '3')
                    .replace('۴', '4')
                    .replace('۵', '5')
                    .replace('۶', '6')
                    .replace('۷', '7')
                    .replace('۸', '8')
                    .replace('۹', '9');
        } else {
            return str.replace('0', '۰')
                    .replace('1', '۱')
                    .replace('2', '۲')
                    .replace('3', '۳')
                    .replace('4', '۴')
                    .replace('5', '۵')
                    .replace('6', '۶')
                    .replace('7', '۷')
                    .replace('8', '۸')
                    .replace('9', '۹');
        }
    }
}
