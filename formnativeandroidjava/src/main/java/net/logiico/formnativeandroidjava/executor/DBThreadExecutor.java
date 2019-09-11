package net.logiico.formnativeandroidjava.executor;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DBThreadExecutor implements Executor {

    private static final DBThreadExecutor ourInstance = new DBThreadExecutor();
    private final Executor mDiskIO;

    private DBThreadExecutor() {
        mDiskIO = Executors.newSingleThreadExecutor();
    }

    public static DBThreadExecutor getInstance() {
        return ourInstance;
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mDiskIO.execute(command);
    }
}
