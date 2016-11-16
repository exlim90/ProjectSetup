package com.projectsetup.vlad.projectsetup.util;

import android.support.v4.widget.SwipeRefreshLayout;

import java.lang.ref.WeakReference;

public class SwipeRefreshLayoutOptions implements Runnable {
    WeakReference<SwipeRefreshLayout> swipeRefreshLayoutOptionsWeakReference;
    boolean refreshing;

    public SwipeRefreshLayoutOptions(SwipeRefreshLayout view, boolean refreshing) {
        this.swipeRefreshLayoutOptionsWeakReference = new WeakReference<>(view);
        this.refreshing = refreshing;
    }

    @Override
    public void run() {
        swipeRefreshLayoutOptionsWeakReference.get().setRefreshing(refreshing);
    }
}