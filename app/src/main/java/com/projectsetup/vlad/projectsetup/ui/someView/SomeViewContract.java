package com.projectsetup.vlad.projectsetup.ui.someView;

import com.projectsetup.vlad.projectsetup.ui.BasePresenter;
import com.projectsetup.vlad.projectsetup.ui.BaseView;

/**
 * Created by Vladimir on 11/14/2016.
 */
public interface SomeViewContract {

    interface View extends BaseView {
        void showMessage(String message);
    }

    interface Presenter extends BasePresenter {

        void onButtonClick();

        void cleanUp();

    }
}