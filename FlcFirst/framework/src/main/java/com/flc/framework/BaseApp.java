package com.flc.framework;

import android.app.Application;

import com.flc.framework.processor.ScreenProcessor;
import com.flc.framework.utils.SingletonFactory;

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
        SingletonFactory.getSingleton(ScreenProcessor.class).init();
    }

    public static BaseApp getInstance() {
        return instance;
    }

}
