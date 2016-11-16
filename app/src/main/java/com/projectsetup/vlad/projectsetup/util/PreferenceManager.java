package com.projectsetup.vlad.projectsetup.util;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.projectsetup.vlad.projectsetup.model.Product;

import java.util.List;

/**
 * Created by Vladimir on 11/14/2016.
 */

public class PreferenceManager implements AppPreferences {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public PreferenceManager(SharedPreferences preferences) {
        this.preferences = preferences;
        editor = preferences.edit();
    }

    public void setUserId(String userId) {
        editor.putString("app.userId", userId);
        editor.commit();
    }

    @Override
    public String getUserId() {
        return preferences.getString("app.userId", "noUserId");
    }

    @Override
    public void clearPreferences() {
        editor.clear();
        editor.commit();
    }

    @Override
    public List<Product> getProducts() {
        String products = preferences.getString("app.products", "[]");
        LogWrapper.d("Preferences", "getProducts()= " + products);
        return new Gson().fromJson(products, new TypeToken<List<Product>>() {
        }.getType());
    }

    @Override
    public void setProducts(List<Product> products) {
        LogWrapper.d("Preferences", "setProducts()");
        editor.putString("app.products", new Gson().toJson(products));
        editor.commit();
    }
}
