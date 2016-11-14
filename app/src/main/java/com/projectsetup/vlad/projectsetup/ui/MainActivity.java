package com.projectsetup.vlad.projectsetup.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.projectsetup.vlad.projectsetup.App;
import com.projectsetup.vlad.projectsetup.R;
import com.projectsetup.vlad.projectsetup.util.AppPreferences;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.textView1)
    TextView textView1;

    @Inject
    AppPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.setDebug(true);
        ButterKnife.bind(this);
        App.getAppComponent().inject(this);

        textView1.setText("app user: " + preferences.getUserId());
    }
}
