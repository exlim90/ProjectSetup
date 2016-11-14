package com.projectsetup.vlad.projectsetup;

import android.content.Context;

import com.projectsetup.vlad.projectsetup.ui.MainActivity;
import com.projectsetup.vlad.projectsetup.util.AppPreferences;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by vladi on 11/12/2016.
 */
@Singleton
@Component(modules = {NetworkModule.class, AppModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);

    NetworkApi getNetworkApi();

    Context applicationContext();

    AppPreferences appPreferences();
}
