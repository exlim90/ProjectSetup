package com.projectsetup.vlad.projectsetup.ui.mainView;

import com.projectsetup.vlad.projectsetup.di.NetworkApi;
import com.projectsetup.vlad.projectsetup.model.Product;
import com.projectsetup.vlad.projectsetup.model.ProductItemsResponse;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Vladimir on 11/14/2016.
 */

public class ProductManager {

    private NetworkApi api;

    @Inject
    public ProductManager(NetworkApi api) {
        this.api = api;
    }

    public Observable<List<Product>> getProducts() {
        return api.getProducts().map(ProductItemsResponse::getItems);
    }
}
