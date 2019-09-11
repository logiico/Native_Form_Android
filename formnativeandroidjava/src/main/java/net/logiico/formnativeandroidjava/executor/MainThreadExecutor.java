package net.logiico.formnativeandroidjava.executor;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;

public class MainThreadExecutor implements Executor {
    private static final MainThreadExecutor ourInstance = new MainThreadExecutor();
    private Handler mainThreadHandler;


    private MainThreadExecutor() {
        mainThreadHandler = new Handler(Looper.getMainLooper());
    }

    public static MainThreadExecutor getInstance() {
        return ourInstance;
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mainThreadHandler.post(command);
    }
}
