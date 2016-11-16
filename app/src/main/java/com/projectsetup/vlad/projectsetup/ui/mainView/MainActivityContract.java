package com.projectsetup.vlad.projectsetup.ui.mainView;

import com.projectsetup.vlad.projectsetup.model.Product;
import com.projectsetup.vlad.projectsetup.ui.BasePresenter;
import com.projectsetup.vlad.projectsetup.ui.BaseView;

import java.util.List;

/**
 * Created by Vladimir on 11/14/2016.
 */
public interface MainActivityContract {

    interface View extends BaseView {
        void showMessage(String message);

        void showLoadingIndicator(boolean show);

        void showErrorMessage(String message, boolean retry);

        void bindData(List<Product> products);

        void onItemClick(String imageUrl);
    }

    interface Presenter extends BasePresenter {

        void getProducts();

        void cleanUp();

        List<Product> getProductsList();
    }
}