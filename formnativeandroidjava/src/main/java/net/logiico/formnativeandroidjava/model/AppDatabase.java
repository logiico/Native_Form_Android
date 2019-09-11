package net.logiico.formnativeandroidjava.model;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import net.logiico.formnativeandroidjava.MyApplication;

@Database(entities = {RoomTemplateResult.class, RoomFileModel.class, RoomTemplate.class},
        version = 2, exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE 'MissionAttachment' ADD COLUMN number_of_failed_sends INTEGER NOT NULL default 0");
        }
    };
    private static final String DATABASE_NAME = "room-database";
    private static AppDatabase instance = null;

    public static AppDatabase getAppDatabase() {
        if (instance == null) {
            instance = Room.databaseBuilder(MyApplication.getInstance(), AppDatabase.class, DATABASE_NAME)
                    .addMigrations(MIGRATION_1_2)
                    .build();
        }
        return instance;
    }


    public abstract TemplateResultDao templateResultDao();

    public abstract FileModelDao fileModelDao();

    public abstract TemplateDao templateDao();

}

