package com.flc.framework;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by channagihong on 08/01/2018.
 */

public class BaseApp extends Application {

    private static volatile BaseApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Timber.plant(new Timber.DebugTree());
    }

    public static BaseApp getInstance() {
        return instance;
    }

}
