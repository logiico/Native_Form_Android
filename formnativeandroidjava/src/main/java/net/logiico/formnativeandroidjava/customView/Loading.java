package net.logiico.formnativeandroidjava.customView;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import net.logiico.formnativeandroidjava.R;

public class Loading extends ImageView {
    public Loading(Context context) {
        super(context);
        init();
    }

    public Loading(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Loading(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Loading(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    void init() {
        setBackgroundResource(R.drawable.loading);
        AnimationDrawable rocketAnimation = (AnimationDrawable) getBackground();
        rocketAnimation.start();
    }
}
