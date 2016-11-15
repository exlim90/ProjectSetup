package com.projectsetup.vlad.projectsetup.ui.someView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.projectsetup.vlad.projectsetup.App;
import com.projectsetup.vlad.projectsetup.R;
import com.projectsetup.vlad.projectsetup.ui.BaseActivity;
import com.projectsetup.vlad.projectsetup.ui.someView.di.DaggerSomeViewComponent;
import com.projectsetup.vlad.projectsetup.ui.someView.di.SomeViewModule;
import com.projectsetup.vlad.projectsetup.util.AppPreferences;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Vladimir on 11/14/2016.
 */
public class MainActivity extends BaseActivity implements SomeViewContract.View {
    public static final String TAG = MainActivity.class.getName();

    @BindView(R.id.textView1)
    TextView textView1;

    @BindView(R.id.button)
    Button button;

    @Inject
    AppPreferences preferences;

    @Inject
    SomeViewContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        injectUIDependencies();
        presenter.onCreate();
    }

    @Override
    protected void injectUIDependencies() {
        ButterKnife.setDebug(true);
        ButterKnife.bind(this);
    }

    public void injectDaggerDependencies() {
        DaggerSomeViewComponent.builder().appComponent(App.getAppComponent()).someViewModule(new SomeViewModule(this)).build().inject(this);
    }

    @Override
    public void showMessage(String message) {
        textView1.setText(textView1.getText().toString() + "\n" + message);
    }

    @Override
    public boolean isActive() {
        return !isFinishing();
    }

    @Override
    protected void onResume() {
        super.onResume();

        textView1.setText("Saying hello user= " + preferences.getUserId());
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

    @OnClick(R.id.button)
    public void onButtonClick() {
        startActivity(this);
    }

    public static void startActivity(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null !");
        }
        Intent i = new Intent(context, MainActivity.class);
        context.startActivity(i);
    }
}
