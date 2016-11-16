package com.projectsetup.vlad.projectsetup.ui.mainView.di;

import com.projectsetup.vlad.projectsetup.di.AppComponent;
import com.projectsetup.vlad.projectsetup.ui.mainView.MainActivity;
import com.projectsetup.vlad.projectsetup.util.AppPreferences;

import dagger.Component;

/**
 * Created by Vladimir on 11/14/2016.
 */

@ActivityScoped
@Component(dependencies = AppComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {

    AppPreferences appPreferences();

    void inject(MainActivity mainActivity);
}
