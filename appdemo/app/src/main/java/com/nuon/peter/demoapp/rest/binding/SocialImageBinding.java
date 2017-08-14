package com.nuon.peter.demoapp.rest.binding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by manithnuon on 6/22/17.
 */

public class SocialImageBinding {

    @BindingAdapter("imagecirlce")
    public static void setImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).bitmapTransform(new CropCircleTransformation(view.getContext()))
                .into(view);
    }
}
