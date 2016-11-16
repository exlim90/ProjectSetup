package com.projectsetup.vlad.projectsetup.model;

/**
 * Created by vladi on 11/15/2016.
 */

public class Price {

    private double current;
    private double original;
    private String currency;

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    public double getOriginal() {
        return original;
    }

    public void setOriginal(double original) {
        this.original = original;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
