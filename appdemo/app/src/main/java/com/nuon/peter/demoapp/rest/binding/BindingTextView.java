package com.nuon.peter.demoapp.rest.binding;

import android.databinding.BindingAdapter;
import android.widget.TextView;

/**
 * Created by manithnuon on 6/16/17.
 */

public class BindingTextView {
    @BindingAdapter("title")
    public static void BindText(TextView view, String text){
        view.setText(text);
    }
}
