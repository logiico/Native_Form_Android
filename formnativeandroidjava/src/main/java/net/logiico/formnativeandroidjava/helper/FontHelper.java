package net.logiico.formnativeandroidjava.helper;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FontHelper {
    public static final int IRANSansMobile_Medium = 5;
    static Typeface iransansmedium;
    public static Typeface get(Context paramContext, int fontNum) {

        if (fontNum == IRANSansMobile_Medium) {
            if (iransansmedium == null)
                iransansmedium = Typeface.createFromAsset(paramContext.getAssets(), "IRANSansMobile_Medium.ttf");
            return iransansmedium;
        }
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
