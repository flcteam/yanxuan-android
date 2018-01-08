package com.flc.first;

import com.flc.framework.BaseApp;

/**
 * Created by channagihong on 08/01/2018.
 */

public class App extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static App getInstance() {
        return (App) BaseApp.getInstance();
    }

}
