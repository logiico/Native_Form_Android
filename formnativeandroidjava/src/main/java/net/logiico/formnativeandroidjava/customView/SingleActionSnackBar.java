package net.logiico.formnativeandroidjava.customView;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.google.android.material.snackbar.Snackbar;

import net.logiico.formnativeandroidjava.MyApplication;
import net.logiico.formnativeandroidjava.R;

public class SingleActionSnackBar {

    private static Snackbar snackBarObject;

    public static void show(View root, int backgroundColor, int buttonTextColor, String message, String actionTitleFirst, View.OnClickListener onClickListenerFirst) {
        Snackbar snackbar;

        View viewPos = root.findViewById(root.getContext().getResources().getIdentifier("snack_bar_coordinator_layout","id",root.getContext().getPackageName()));
        if (viewPos != null) {
            snackbar = Snackbar.make(viewPos, message, Snackbar.LENGTH_INDEFINITE);
        } else {
            snackbar = Snackbar.make(root, message, Snackbar.LENGTH_INDEFINITE);

        }

        (snackbar.getView()).findViewById(com.google.android.material.R.id.snackbar_text).setVisibility(View.INVISIBLE);
        (snackbar.getView()).findViewById(com.google.android.material.R.id.snackbar_action).setVisibility(View.INVISIBLE);
        (snackbar.getView()).setBackgroundColor(Color.TRANSPARENT);

        snackBarObject = snackbar;

        View snackView = ((LayoutInflater) root.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.snack_bar_single_action, ((Snackbar.SnackbarLayout) snackbar.getView()));
        ((TextView) snackView.findViewById(R.id.message)).setText(message);

        if (backgroundColor != 0) {
            ((CardView) snackView.findViewById(R.id.card_view)).setCardBackgroundColor(backgroundColor);
        } else {
            ((CardView) snackView.findViewById(R.id.card_view)).setCardBackgroundColor(MyApplication.getInstance().activity.getResources().getColor(R.color.snack_bar_background));
        }

        if (buttonTextColor != 0) {
            ((TextView) snackView.findViewById(R.id.action_button)).setTextColor(buttonTextColor);
        }


        if (actionTitleFirst != null && !actionTitleFirst.equals("") && onClickListenerFirst != null) {
            ((TextView) snackView.findViewById(R.id.action_button)).setText(actionTitleFirst);
            snackView.findViewById(R.id.action_button).setOnClickListener(onClickListenerFirst);

        } else {
            snackView.findViewById(R.id.action_button).setVisibility(View.GONE);
        }

        snackbar.show();
    }

    public static void dismiss() {
        snackBarObject.dismiss();
    }
}
