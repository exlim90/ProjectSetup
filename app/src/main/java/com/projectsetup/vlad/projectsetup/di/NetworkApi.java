package com.projectsetup.vlad.projectsetup.di;

import com.projectsetup.vlad.projectsetup.model.ProductItemsResponse;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Vladimir on 11/14/2016.
 */

public interface NetworkApi {
    public static final String BASE_URL = "http://private-f8130-recruitmenttestb.apiary-mock.com/v1/";

    @GET("products")
    Observable<ProductItemsResponse> getProducts();
}
