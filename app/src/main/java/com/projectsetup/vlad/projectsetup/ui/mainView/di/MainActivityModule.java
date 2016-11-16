package com.projectsetup.vlad.projectsetup.ui.mainView.di;


import com.projectsetup.vlad.projectsetup.di.NetworkApi;
import com.projectsetup.vlad.projectsetup.ui.mainView.ProductManager;
import com.projectsetup.vlad.projectsetup.ui.mainView.MainActivityContract;
import com.projectsetup.vlad.projectsetup.ui.mainView.MainActivityPresenter;
import com.projectsetup.vlad.projectsetup.util.AppPreferences;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Vladimir on 11/14/2016.
 */

@Module
public class MainActivityModule {

    private MainActivityContract.View view;

    public MainActivityModule(MainActivityContract.View view) {
        this.view = view;
    }

    @Provides
    public MainActivityContract.View provideSomeViewContractView() {
        return view;
    }

    @Provides
    public MainActivityContract.Presenter provideSomeViewContractPresenter(ProductManager productManager, MainActivityContract.View view,AppPreferences preferences) {
        return new MainActivityPresenter(preferences,productManager, view);
    }

    @Provides
    public ProductManager provideEmptyManager(NetworkApi api) {
        return new ProductManager(api);
    }
}
