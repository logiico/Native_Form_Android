package com.example.nativeformmakerandroid.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.example.nativeformmakerandroid.R;
import com.example.nativeformmakerandroid.helper.UnitHelper;


public class FitGridView extends GridView {

    public float itemWidth, itemSpace, itemHeight;
    public int rowCount;

    public FitGridView(Context context) {
        super(context);
        init(null, 0);
    }

    public FitGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public FitGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    void init(AttributeSet attrs, int defStyle) {

        if (getRootView().isInEditMode())
            return;

        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.FitGridView, defStyle, 0);

        itemWidth = a.getFloat(R.styleable.FitGridView_itemWidth, 0);

        itemSpace = a.getFloat(R.styleable.FitGridView_itemSpace, 0);

        a.recycle();
    }

    public void setItemWidth(int iv) {
        itemWidth = iv;
    }

    @Override
    public void setAdapter(final ListAdapter adapter) {
        super.setAdapter(adapter);

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (adapter.getCount() != 0) {
                    final int numColumns = (int) Math.floor(getWidth() / (UnitHelper.DipToPixel(getContext(), itemWidth) + UnitHelper.DipToPixel(getContext(), itemSpace)));
                    if (numColumns > 0) {
                        final int columnWidth = (getWidth() / numColumns) - (int) UnitHelper.DipToPixel(getContext(), itemSpace);
                        setNumColumns(numColumns);
                        setColumnWidth(columnWidth);
                    }
                }
            }
        });
    }
}
