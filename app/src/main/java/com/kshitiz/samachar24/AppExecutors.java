package com.kshitiz.samachar24;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {

    static AppExecutors instance = new AppExecutors();

    private final Executor mDiskIO = Executors.newSingleThreadExecutor();

    private final Executor mNetworkIO = Executors.newFixedThreadPool(3);

    private final Executor mMainThread = new MainThreadExecutor();

    public Executor diskIO() {
        return mDiskIO;
    }

    public Executor networkIO() {
        return mNetworkIO;
    }

    public Executor mainThread() {
        return mMainThread;
    }

    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }


    public static AppExecutors get(){
        return instance;
    }
}