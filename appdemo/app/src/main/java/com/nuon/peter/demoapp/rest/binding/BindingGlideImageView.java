package com.nuon.peter.demoapp.rest.binding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nuon.peter.demoapp.rest.Common;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by manithnuon on 6/17/17.
 */

public class BindingGlideImageView {

    @BindingAdapter("glideloader")
    public static void GlideImageLoader (ImageView view, String url){
        Glide.with(view.getContext()).load(Common.IMAGE_URL_LARGE + url)
                .thumbnail(Glide.with(view.getContext())
                        .load(Common.IMAGE_URL_SMALL + url)
                        .bitmapTransform(new BlurTransformation(view.getContext())))
                .dontAnimate()
                .into(view);
    }

    @BindingAdapter("socialimage")
    public static void SocialImageLoader (ImageView view, String url){
        Glide.with(view.getContext()).load(Common.IMAGE_URL_LARGE + url)
                .thumbnail(Glide.with(view.getContext())
                        .load(Common.IMAGE_URL_SMALL + url)
                        .bitmapTransform(new BlurTransformation(view.getContext())))
                .dontAnimate()
                .into(view);
    }

}
