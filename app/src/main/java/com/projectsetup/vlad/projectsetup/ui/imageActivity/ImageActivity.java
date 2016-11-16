package com.projectsetup.vlad.projectsetup.ui.imageActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.projectsetup.vlad.projectsetup.R;
import com.projectsetup.vlad.projectsetup.ui.BaseActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vladi on 11/15/2016.
 */

public class ImageActivity extends BaseActivity {

    private static final String IMAGE_EXTRA = "imageExtra";

    @BindView(R.id.image)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        injectUIDependencies();

        initView();
    }

    @Override
    protected void injectDaggerDependencies() {
        // nothing to do here
    }

    @Override
    protected void injectUIDependencies() {
        ButterKnife.bind(this);
    }

    public static void startActivity(Context context, String imageUrl) {
        Intent i = new Intent(context, ImageActivity.class);
        i.putExtra(IMAGE_EXTRA, imageUrl);
        context.startActivity(i);
    }

    private void initView() {
        String imageUrl = getIntent().getExtras().getString(IMAGE_EXTRA);
        Picasso.with(this).load(imageUrl).error(R.drawable.image_not_found_placeholder).into(imageView);
    }
}
