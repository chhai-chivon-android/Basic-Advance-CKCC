package com.nuon.peter.demoapp.base.component.providers.media.models;


public class Genre {

    private String mKey;
    private int mLabel;

    public Genre(String key, int label) {
        mKey = key;
        mLabel = label;
    }

    public String getKey() {
        return mKey;
    }

    public int getLabelId() {
        return mLabel;
    }

}