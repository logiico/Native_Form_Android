package net.logiico.formnativeandroidjava.dagger;

import net.logiico.formnativeandroidjava.MyApplication;
import net.logiico.formnativeandroidjava.helper.TenthActivity;
import net.logiico.formnativeandroidjava.model.AppDatabase;
import net.logiico.formnativeandroidjava.model.AppModule;
import net.logiico.formnativeandroidjava.model.FileModelDao;
import net.logiico.formnativeandroidjava.model.FileModelRepository;
import net.logiico.formnativeandroidjava.model.RoomModule;
import net.logiico.formnativeandroidjava.model.TemplateDao;
import net.logiico.formnativeandroidjava.model.TemplateRepository;
import net.logiico.formnativeandroidjava.model.TemplateResultDao;
import net.logiico.formnativeandroidjava.model.TemplateResultRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(dependencies = {}, modules = {AppModule.class, RoomModule.class})
public interface AppComponent {
    void inject(MyApplication myApplication);

    void inject(AppDatabase appDatabase);

    void inject(TenthActivity activity);

    void inject(TemplateResultRepository templateResultRepo);

    void inject(TemplateRepository templateRepo);

    void inject(FileModelRepository fileModelRepo);

    TemplateResultDao templateResultDao();

    FileModelDao inspectionFileModelDao();

    TemplateDao templateDao();

    MyApplication application();
}
