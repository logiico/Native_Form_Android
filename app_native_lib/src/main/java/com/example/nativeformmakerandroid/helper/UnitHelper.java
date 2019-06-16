package com.example.nativeformmakerandroid.helper;


import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.nativeformmakerandroid.views.FitGridView;

public class UnitHelper {
    public static float DipToPixel(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }

    public static void setGridViewHeightBasedOnChildren(FitGridView gridView) {
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {

            return;
        }

        int items = listAdapter.getCount();

        int columns = (int) Math.floor(gridView.getWidth() / (UnitHelper.DipToPixel(gridView.getContext(), gridView.itemWidth) + UnitHelper.DipToPixel(gridView.getContext(), gridView.itemSpace)));

        View listItem = listAdapter.getView(0, null, gridView);
        listItem.measure(0, 0);
        int totalHeight = listItem.getMeasuredHeight() + (int) gridView.itemSpace;

        gridView.itemHeight = totalHeight;
        gridView.rowCount = (int) (Math.ceil((float) items / (float) columns));

        totalHeight = (int) (Math.ceil((float) items / (float) columns) * totalHeight);

        totalHeight += gridView.getPaddingTop() + gridView.getPaddingBottom() + 15;

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        gridView.setLayoutParams(params);

    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static void fitView(Context context, View view) {
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        float screen_width = DeviceHelper.GetScreenSize(context).width();
        float delta_width = screen_width - view.getMeasuredWidth();
        view.getLayoutParams().height = (int) (view.getMeasuredHeight() + (delta_width / 1.8));
        view.getLayoutParams().width = (int) (view.getMeasuredWidth() + delta_width);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = Math.max(0, totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1)));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
