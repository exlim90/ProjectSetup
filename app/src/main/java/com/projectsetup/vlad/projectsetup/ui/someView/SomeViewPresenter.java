package com.projectsetup.vlad.projectsetup.ui.someView;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Vladimir on 11/14/2016.
 */
public class SomeViewPresenter implements SomeViewContract.Presenter {

    EmptyManager emptyManager;

    SomeViewContract.View view;
    CompositeSubscription subscriptions = new CompositeSubscription();

    @Inject
    public SomeViewPresenter(EmptyManager emptyManager, SomeViewContract.View view) {
        this.emptyManager = emptyManager;
        this.view = view;
    }

    @Override
    public void onButtonClick() {

    }

    @Override
    public void cleanUp() {
        subscriptions.unsubscribe();
    }

    @Override
    public void onCreate() {
        doSomething();
    }

    @Override
    public void onResume() {
        doSomething();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {
        cleanUp();
    }

    @Override
    public void onDestroy() {
        cleanUp();
    }

    private void doSomething() {
        Subscription subscription = emptyManager.getStrings().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(view::showMessage);
        subscriptions.add(subscription);
    }
}