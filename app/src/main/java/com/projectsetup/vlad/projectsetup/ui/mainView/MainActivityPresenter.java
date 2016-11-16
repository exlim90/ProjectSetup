package com.projectsetup.vlad.projectsetup.ui.mainView;

import com.projectsetup.vlad.projectsetup.model.Product;
import com.projectsetup.vlad.projectsetup.util.AppPreferences;
import com.projectsetup.vlad.projectsetup.util.LogWrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by Vladimir on 11/14/2016.
 */
public class MainActivityPresenter implements MainActivityContract.Presenter {

    ProductManager productManager;
    MainActivityContract.View view;
    AppPreferences preferences;

    Subscription subscription = Subscriptions.empty();

    private List<Product> products;

    @Inject
    public MainActivityPresenter(AppPreferences preferences, ProductManager productManager, MainActivityContract.View view) {
        this.productManager = productManager;
        this.view = view;
        this.preferences = preferences;
        products = new ArrayList<>(preferences.getProducts());
    }

    @Override
    public void cleanUp() {
        subscription.unsubscribe();
    }

    @Override
    public List<Product> getProductsList() {
        return products;
    }

    @Override
    public void onCreate() {
        getProducts();
    }

    @Override
    public void onResume() {

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

    public void getProducts() {
        LogWrapper.d("MainPresenter", "getProducts()");
        view.showLoadingIndicator(true);
        subscription = productManager.getProducts()/*.toSortedList(Product::compareByName)*/.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(this::onSuccessProductsResponse, this::onError, this::onComplete);
    }


    private void onSuccessProductsResponse(List<Product> newProducts) {
        preferences.setProducts(newProducts);
        products.clear();
        products.addAll(newProducts);

        view.showLoadingIndicator(false);
        view.bindData(products);
    }

    private void onError(Throwable t) {
        view.showLoadingIndicator(false);
        view.showErrorMessage("Please try again !", true);
        t.printStackTrace();
        LogWrapper.e("asd", t.getMessage());
    }

    private void onComplete() {
        view.showLoadingIndicator(false);
    }
}