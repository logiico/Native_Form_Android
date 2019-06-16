package com.example.nativeformmakerandroid.helper;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FontHelper {

    public static final int BNazanin = 1;
    public static final int BYekan = 2;
    public static final int BYekanBold = 3;
    public static final int DroidArabicNaskh = 4;
    public static final int IRANSansMobile_Medium = 5;
    public static final int IRANSansMobile_UltraLight = 6;
    public static final int IRANSansMobile_Bold = 7;
    public static final int IRANSansMobile_FaNum = 8;

    static Typeface yekan, yekanBold, droid, nazanin, iransansmedium, iransansultralight, iransansbold, iransansfanum;

    public static Typeface get(Context paramContext, int fontNum) {

       /* if (fontNum == BNazanin) {
            if (nazanin == null)
                nazanin = Typeface.createFromAsset(paramContext.getAssets(), "BNazanin.ttf");
            return nazanin;
        } else if (fontNum == BYekan) {
            if (yekan == null)
                yekan = Typeface.createFromAsset(paramContext.getAssets(), "BYekan.ttf");
            return yekan;
        } else if (fontNum == BYekanBold) {
            if (yekanBold == null)
                yekanBold = Typeface.createFromAsset(paramContext.getAssets(), "BYekanBold.ttf");
            return yekanBold;
        } else if (fontNum == DroidArabicNaskh) {
            if (droid == null)
                droid = Typeface.createFromAsset(paramContext.getAssets(), "DroidArabicNaskh.ttf");
            return droid;
        } else*/
        if (fontNum == IRANSansMobile_Medium) {
            if (iransansmedium == null)
                iransansmedium = Typeface.createFromAsset(paramContext.getAssets(), "IRANSansMobile_Medium.ttf");
            return iransansmedium;
        } else if (fontNum == IRANSansMobile_FaNum) {
            if (iransansfanum == null)
                iransansfanum = Typeface.createFromAsset(paramContext.getAssets(), "IRANSansMobile(FaNum).ttf");
            return iransansfanum;
        }/*  else if (fontNum == IRANSansMobile_UltraLight) {
            if (iransansultralight == null)
                iransansultralight = Typeface.createFromAsset(paramContext.getAssets(), "IRANSansMobile_UltraLight.ttf");
            return iransansultralight;
        } else if (fontNum == IRANSansMobile_Bold) {
            if (iransansbold == null)
                iransansbold = Typeface.createFromAsset(paramContext.getAssets(), "IRANSansMobile_Bold.ttf");
            return iransansbold;
        }
*/
        return iransansmedium;
    }

    public static void overrideFonts(final Context context, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFonts(context, child);
                }
            } else if (v instanceof TextView) {
                ((TextView) v).setTypeface(get(context, ConstHelper.DEFAULT_FONT));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void overrideFonts(final Context context, final View v, float textSize) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFonts(context, child, textSize);
                }
            } else if (v instanceof TextView) {
                ((TextView) v).setTypeface(get(context, ConstHelper.DEFAULT_FONT));
                ((TextView) v).setTextSize(textSize);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
