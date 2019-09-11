package net.logiico.formnativeandroidjava.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageButton;

import net.logiico.formnativeandroidjava.R;

public class TenthImageButton extends AppCompatImageButton {
    public TenthImageButton(Context context) {
        super(context);
        init(null, 0);
    }

    public TenthImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public TenthImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyle) {
        int[] attrs1;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            attrs1 = new int[]{R.attr.selectableItemBackgroundBorderless};
        else
            attrs1 = new int[]{R.attr.selectableItemBackground};
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs1);
        int backgroundResource = typedArray.getResourceId(0, 0);
        setBackgroundResource(backgroundResource);
        typedArray.recycle();

        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.TenthImageButton, defStyle, 0);
        int padding = (int) a.getDimension(R.styleable.TenthImageButton_padding, 0);
        setPadding(padding, padding, padding, padding);
        a.recycle();
    }
}
