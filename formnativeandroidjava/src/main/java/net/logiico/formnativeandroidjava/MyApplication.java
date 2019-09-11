package net.logiico.formnativeandroidjava;

import android.app.Application;

import net.logiico.formnativeandroidjava.dagger.DaggerAppComponent;
import net.logiico.formnativeandroidjava.helper.LogHelper;
import net.logiico.formnativeandroidjava.helper.TenthActivity;
import net.logiico.formnativeandroidjava.dagger.AppComponent;
import net.logiico.formnativeandroidjava.model.AppModule;
import net.logiico.formnativeandroidjava.model.RoomModule;

public class MyApplication extends Application {
    private static MyApplication instance;
    private AppComponent appComponent;
    public TenthActivity activity;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .roomModule(new RoomModule(this))
                .build();
        appComponent.inject(this);
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public void setcontext(TenthActivity context) {
        activity = context;
        if (BuildConfig.DEBUG)
            LogHelper.v(activity.getPackageName(), "you are now in activity : " + context.getClass().getSimpleName());
    }

}
