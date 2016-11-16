package com.projectsetup.vlad.projectsetup.ui.mainView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Surface;
import android.view.WindowManager;

import com.projectsetup.vlad.projectsetup.App;
import com.projectsetup.vlad.projectsetup.R;
import com.projectsetup.vlad.projectsetup.adapter.ProductsAdapter;
import com.projectsetup.vlad.projectsetup.model.Product;
import com.projectsetup.vlad.projectsetup.ui.BaseActivity;
import com.projectsetup.vlad.projectsetup.ui.imageActivity.ImageActivity;
import com.projectsetup.vlad.projectsetup.ui.mainView.di.DaggerMainActivityComponent;
import com.projectsetup.vlad.projectsetup.ui.mainView.di.MainActivityModule;
import com.projectsetup.vlad.projectsetup.util.AppPreferences;
import com.projectsetup.vlad.projectsetup.util.LogWrapper;
import com.projectsetup.vlad.projectsetup.util.MultiSwipeRefreshLayout;
import com.projectsetup.vlad.projectsetup.util.SwipeRefreshLayoutOptions;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vladimir on 11/14/2016.
 */
public class MainActivity extends BaseActivity implements MainActivityContract.View, SwipeRefreshLayout.OnRefreshListener, ProductsAdapter.OnProductsItemClick {
    public static final String TAG = MainActivity.class.getName();

    @Inject
    AppPreferences preferences;

    @Inject
    MainActivityContract.Presenter presenter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    ProductsAdapter adapter;

    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.swipeToRefresh)
    MultiSwipeRefreshLayout swipeRefreshLayout;

    ProductsAdapter.OnProductsItemClick onProductsItemClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        injectUIDependencies();
        initView();
        presenter.onCreate();
    }

    private void initView() {
        onProductsItemClick = this;
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setSwipeableChildren(R.id.recyclerView);

        adapter = new ProductsAdapter(presenter.getProductsList(), onProductsItemClick);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(getLayoutManagerAccordingToOrientation());
    }

    private LinearLayoutManager getLayoutManagerAccordingToOrientation() {
        final int screenOrientation = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getOrientation();
        switch (screenOrientation) {
            case Surface.ROTATION_0:
                return new LinearLayoutManager(getApplicationContext());
            case Surface.ROTATION_90:
                return new GridLayoutManager(getApplicationContext(), 2);
            case Surface.ROTATION_180:
                return new LinearLayoutManager(getApplicationContext());
            default:
                return new GridLayoutManager(getApplicationContext(), 2);
        }
    }

    @Override
    protected void injectUIDependencies() {
        ButterKnife.setDebug(true);
        ButterKnife.bind(this);
    }

    public void injectDaggerDependencies() {
        DaggerMainActivityComponent.builder().appComponent(App.getAppComponent()).mainActivityModule(new MainActivityModule(this)).build().inject(this);
    }

    @Override
    public void showMessage(String message) {
    }

    @Override
    public void showLoadingIndicator(boolean show) {
        LogWrapper.d(TAG, "showLoadingIndicator =" + show);
        swipeRefreshLayout.post(new SwipeRefreshLayoutOptions(swipeRefreshLayout, show));
    }

    @Override
    public void showErrorMessage(String message, boolean retry) {
        if (retry) {
            Snackbar snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG)
                    .setAction("RETRY", view -> {
                        presenter.getProducts();
                    });
            snackbar.show();
        }

    }

    @Override
    public void bindData(List<Product> products) {
        LogWrapper.d(TAG, "bindData size=" + products.size());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(String imageUrl) {
        ImageActivity.startActivity(this, imageUrl);
    }

    @Override
    public boolean isActive() {
        return !isFinishing();
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }


    public static void startActivity(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null !");
        }
        Intent i = new Intent(context, MainActivity.class);
        context.startActivity(i);
    }

    @Override
    public void onRefresh() {
        LogWrapper.d(TAG, "onRefresh()");
        presenter.getProducts();
    }

    @Override
    public void onProductClick(String imageUrl) {
        onItemClick(imageUrl);
    }
}
