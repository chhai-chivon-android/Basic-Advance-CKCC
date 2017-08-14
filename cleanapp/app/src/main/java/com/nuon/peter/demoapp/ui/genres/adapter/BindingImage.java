package com.nuon.peter.demoapp.ui.genres.adapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nuon.peter.demoapp.ui.genres.api.GenreAPI;

/**
 * Created by manithnuon on 2/17/17.
 */

public class BindingImage {
  @BindingAdapter({"image"})
  public static void imageLoader(ImageView imageView, String url) {
    //How to replace the Glide
    Glide.with(imageView.getContext()).load(GenreAPI.getImageURL("w600")+url)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.RESULT)
        .crossFade()
        .into(imageView);
  }
}
