package net.logiico.formnativeandroidjava.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import net.logiico.formnativeandroidjava.R;
import net.logiico.formnativeandroidjava.helper.UnitHelper;

public class TenthToolbar extends Toolbar {
    public TenthToolbar(Context context) {
        super(context);
        setContentInsetsRelative(0, 0);
        setContentInsetsAbsolute(0, 0);
        init(null, 0);
    }

    public TenthToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setContentInsetsRelative(0, 0);
        setContentInsetsAbsolute(0, 0);
        init(attrs, 0);
    }

    public TenthToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setContentInsetsRelative(0, 0);
        setContentInsetsAbsolute(0, 0);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyle) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            final TypedArray a = getContext().obtainStyledAttributes(
                    attrs, R.styleable.TenthToolbar, defStyle, 0);
            int elevation = (int) a.getDimension(R.styleable.TenthToolbar_elevation, UnitHelper.DipToPixel(getContext(), 4));
            setElevation(elevation);
            a.recycle();

        }
    }
}
