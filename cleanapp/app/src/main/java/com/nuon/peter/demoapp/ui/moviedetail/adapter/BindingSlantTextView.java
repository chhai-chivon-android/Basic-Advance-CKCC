package com.nuon.peter.demoapp.ui.moviedetail.adapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nuon.peter.demoapp.ui.genres.api.GenreAPI;
import com.nuon.peter.demoapp.utils.views.slantextview.SlantedTextView;

/**
 * Created by manithnuon on 2/17/17.
 */

public class BindingSlantTextView {
  @BindingAdapter({"slantedText"})
  public static void setText(SlantedTextView textView, double num) {
    textView.setText(String.valueOf(num));
  }
}
