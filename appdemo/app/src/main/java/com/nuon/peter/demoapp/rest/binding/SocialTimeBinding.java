package com.nuon.peter.demoapp.rest.binding;

import android.databinding.BindingAdapter;
import android.text.format.DateUtils;
import android.widget.TextView;

/**
 * Created by manithnuon on 6/22/17.
 */

public class SocialTimeBinding {
    @BindingAdapter("timer")
    public static void setTime(TextView view, Object timestamp) {
        view.setText(DateUtils.getRelativeTimeSpanString(
                (long) timestamp).toString());
    }
}
