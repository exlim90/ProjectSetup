package com.projectsetup.vlad.projectsetup.ui.someView.di;


import com.projectsetup.vlad.projectsetup.di.NetworkApi;
import com.projectsetup.vlad.projectsetup.ui.someView.EmptyManager;
import com.projectsetup.vlad.projectsetup.ui.someView.SomeViewContract;
import com.projectsetup.vlad.projectsetup.ui.someView.SomeViewPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Vladimir on 11/14/2016.
 */

@Module
public class SomeViewModule {

    private SomeViewContract.View view;

    public SomeViewModule(SomeViewContract.View view) {
        this.view = view;
    }

    @Provides
    public SomeViewContract.View provideSomeViewContractView() {
        return view;
    }

    @Provides
    public SomeViewContract.Presenter provideSomeViewContractPresenter(EmptyManager emptyManager, SomeViewContract.View view) {
        return new SomeViewPresenter(emptyManager, view);
    }

    @Provides
    public EmptyManager provideEmptyManager(NetworkApi api) {
        return new EmptyManager(api);
    }
}
