package net.logiico.formnativeandroidjava.dagger;

import net.logiico.formnativeandroidjava.activity.FormNativeActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class)
public interface FormNativeActivityComponent {
    void inject(FormNativeActivity activity);

}
