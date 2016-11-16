package com.projectsetup.vlad.projectsetup.model;

import java.util.Comparator;

/**
 * Created by vladi on 11/15/2016.
 */

public class Product {

    private String sku;
    private String name;
    private Price price;

    private String brand;
    private String image;
    private String published_at;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    public static Integer compareByName(Product p1, Product p2) {
        return p1.getName().compareTo(p2.getName());
    }
}
