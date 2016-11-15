package com.projectsetup.vlad.projectsetup.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.projectsetup.vlad.projectsetup.App;
import com.projectsetup.vlad.projectsetup.R;

import butterknife.ButterKnife;

/**
 * Created by Vladimir on 11/14/2016.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDaggerDependencies();
    }

    protected abstract void injectDaggerDependencies();

    protected abstract void injectUIDependencies();
}
