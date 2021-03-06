package com.projectsetup.vlad.projectsetup.di;

import android.content.Context;

import com.projectsetup.vlad.projectsetup.ui.BaseActivity;
import com.projectsetup.vlad.projectsetup.ui.someView.MainActivity;
import com.projectsetup.vlad.projectsetup.util.AppPreferences;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Vladimir on 11/14/2016.
 */
@Singleton
@Component(modules = {NetworkModule.class, AppModule.class})
public interface AppComponent {

    NetworkApi getNetworkApi();

    Context applicationContext();

    AppPreferences appPreferences();
}
