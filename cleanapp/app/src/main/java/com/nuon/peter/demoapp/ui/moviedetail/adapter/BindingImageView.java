package com.nuon.peter.demoapp.ui.moviedetail.adapter;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nuon.peter.demoapp.ui.genres.api.GenreAPI;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by manithnuon on 2/17/17.
 */

public class BindingImageView {
  @BindingAdapter({"img"})
  public static void imageLoader(ImageView imageView, String url) {
    //How to replace the Glide
    Transformation<Bitmap> bitmapTransformation = new CropCircleTransformation(imageView.getContext());
    Glide.with(imageView.getContext()).load(GenreAPI.getImageURL("w300") + url)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.RESULT)
        .crossFade()
        .bitmapTransform(bitmapTransformation)
        .into(imageView);
  }
}
