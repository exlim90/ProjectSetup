package com.projectsetup.vlad.projectsetup.util;

import com.projectsetup.vlad.projectsetup.model.Product;

import java.util.List;

/**
 * Created by Vladimir on 11/14/2016.
 */
public interface AppPreferences {

    void setUserId(String userId);

    String getUserId();

    void clearPreferences();

    void setProducts(List<Product> products);

    List<Product> getProducts();
}
