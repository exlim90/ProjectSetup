package com.projectsetup.vlad.projectsetup.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.florent37.picassopalette.PicassoPalette;
import com.projectsetup.vlad.projectsetup.R;
import com.projectsetup.vlad.projectsetup.model.Product;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    public interface OnProductsItemClick {
        void onProductClick(String imageUrl);
    }

    private List<Product> data;
    private OnProductsItemClick listener;


    public ProductsAdapter(List<Product> data, OnProductsItemClick listener) {
        this.data = data;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = data.get(position);
        bindData(product, holder);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private void bindData(Product product, ViewHolder holder) {
        holder.title.setText(product.getName());
        holder.brand.setText(product.getBrand());

        if (product.getPrice().getCurrent() == product.getPrice().getOriginal()) {
            holder.currentPrice.setText(getCurrencySymbol(product.getPrice().getCurrency()) + product.getPrice().getCurrent());
            holder.originalPrice.setVisibility(View.GONE);
        } else {
            holder.originalPrice.setVisibility(View.VISIBLE);
            holder.originalPrice.setText(getCurrencySymbol(product.getPrice().getCurrency()) + product.getPrice().getOriginal());
            holder.currentPrice.setText(getCurrencySymbol(product.getPrice().getCurrency()) + product.getPrice().getCurrent());
        }

        String[] images = product.getImage().split("http:");
        /*Picasso.with(holder.image.getContext()).load("http:" + images[2]).resize(150,100).error(R.drawable.image_not_found_placeholder).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                if (holder != null) {
                    holder.image.setImageBitmap(bitmap);
                    Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                        public void onGenerated(Palette palette) {
                            Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
                            if (vibrantSwatch != null) {
                                holder.itemView.setBackgroundColor(vibrantSwatch.getRgb());
                            }
                        }
                    });
                }
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });*/
        holder.itemView.setBackgroundColor(holder.itemView.getContext().getResources().getColor(android.R.color.white));
        Picasso.with(holder.image.getContext()).load("http:" + images[2]).resize(100, 150).error(R.drawable.image_not_found_placeholder).into(holder.image, PicassoPalette.with("http:" + images[2], holder.image)
                .use(PicassoPalette.Profile.MUTED)
                .intoBackground(holder.itemView)
        );


        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onProductClick("http:" + images[2]);
            }
        });

    }


    private static class OnImageLoadListener implements Target {
        private WeakReference<ViewHolder> holderWeakReference;

        public OnImageLoadListener(ViewHolder holder) {
            this.holderWeakReference = new WeakReference<ViewHolder>(holder);
        }

        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            ViewHolder holder = holderWeakReference.get();
            if (holder != null) {
                holder.image.setImageBitmap(bitmap);
                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    public void onGenerated(Palette palette) {
                        Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
                        if (vibrantSwatch != null) {
                            holder.itemView.setBackgroundColor(vibrantSwatch.getRgb());
                        }
                    }
                });
            }
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    }

    private String getCurrencySymbol(String currency) {
        if (currency.toLowerCase().equals("eur")) {
            return "\u20ac ";
        }

        if (currency.toLowerCase().equals("usd")) {
            return "$ ";
        }

        return "$";
    }

    public static List<String> extractUrls(String text) {
        List<String> containedUrls = new ArrayList<String>();
        String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(text);

        while (urlMatcher.find()) {
            containedUrls.add(text.substring(urlMatcher.start(0),
                    urlMatcher.end(0)));
        }

        return containedUrls;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.currentPrice)
        TextView currentPrice;
        @BindView(R.id.originalPrice)
        TextView originalPrice;
        @BindView(R.id.brend)
        TextView brand;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}