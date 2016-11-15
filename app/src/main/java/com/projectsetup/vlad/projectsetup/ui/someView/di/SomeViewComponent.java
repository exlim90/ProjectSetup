package com.projectsetup.vlad.projectsetup.ui.someView.di;

import com.projectsetup.vlad.projectsetup.di.AppComponent;
import com.projectsetup.vlad.projectsetup.ui.someView.MainActivity;

import dagger.Component;

/**
 * Created by Vladimir on 11/14/2016.
 */

@ActivityScoped
@Component(dependencies = AppComponent.class, modules = SomeViewModule.class)
public interface SomeViewComponent {

    void inject(MainActivity mainActivity);
}
