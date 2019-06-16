package com.example.nativeformmakerandroid.helper;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.nativeformmakerandroid.R;

/**
 * Created by mbr on 2/15/17.
 */

public class SnackarHelperByColor {
    private static Snackbar snackbar_obj;

    public static void show(View root, String message, String actionName, int colorOdescrText, int colorOfBtnText, View.OnClickListener onClickListener) {
        Snackbar snackbar = Snackbar.make(root, message, Snackbar.LENGTH_INDEFINITE);
        snackbar_obj = snackbar;
        (snackbar.getView()).setBackgroundColor(colorOdescrText);
        (snackbar.getView()).findViewById(android.support.design.R.id.snackbar_text).setVisibility(View.INVISIBLE);
        (snackbar.getView()).findViewById(android.support.design.R.id.snackbar_action).setVisibility(View.INVISIBLE);
        View snackView = ((LayoutInflater) root.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.snack_bar_custom, null);
        // TextView onclickBtn =( TextView ) root.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.snack_bar_custom, null);
        ((TextView) snackView.findViewById(R.id.snackbar_textview)).setText(message);
        ((TextView) snackView.findViewById(R.id.snackbar_clicked)).setTextColor(colorOfBtnText);

        if (actionName != null && !actionName.equals("") && onClickListener != null) {
            ((TextView) snackView.findViewById(R.id.snackbar_clicked)).setText(actionName);
            snackView.findViewById(R.id.snackbar_clicked).setOnClickListener(onClickListener);
        } else {
            snackView.findViewById(R.id.snackbar_clicked).setVisibility(View.GONE);
        }
        ((Snackbar.SnackbarLayout) snackbar.getView()).addView(snackView);
        snackbar.show();
    }

    public static void dismiss() {
        snackbar_obj.dismiss();
    }
}
