package com.projectsetup.vlad.projectsetup;

import android.app.Application;

/**
 * Created by vladi on 11/12/2016.
 */

public class App extends Application {
    static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().networkModule(new NetworkModule(NetworkApi.BASE_URL)).appModule(new AppModule(getApplicationContext())).build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

}
