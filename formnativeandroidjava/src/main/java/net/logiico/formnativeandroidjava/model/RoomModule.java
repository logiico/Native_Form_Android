package net.logiico.formnativeandroidjava.model;

import net.logiico.formnativeandroidjava.MyApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {
    private static final String DATABASE_NAME = "room-database";

    private AppDatabase appDatabase;

    public RoomModule(MyApplication myApplication) {
//        if (appDatabase == null) {
//            appDatabase = Room.databaseBuilder(myApplication, AppDatabase.class, DATABASE_NAME)
//                    .addMigrations(MIGRATION_1_2)
//                    .build();
//        }
        appDatabase = AppDatabase.getAppDatabase();
    }

    @Singleton
    @Provides
    AppDatabase providesRoomDatabase() {
        return appDatabase;
    }


    @Singleton
    @Provides
    TemplateResultDao providesTemplateResultDao(AppDatabase appDatabase) {
        return appDatabase.templateResultDao();
    }


    @Singleton
    @Provides
    FileModelDao providesFileModelDao(AppDatabase appDatabase) {
        return appDatabase.fileModelDao();
    }



    @Singleton
    @Provides
    TemplateDao providesTemplateDao(AppDatabase appDatabase) {
        return appDatabase.templateDao();
    }

}
