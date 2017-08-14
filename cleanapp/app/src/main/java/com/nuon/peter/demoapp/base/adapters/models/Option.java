package com.nuon.peter.demoapp.base.adapters.models;

import com.nuon.peter.demoapp.utils.LocaleUtils;

public class Option implements Comparable<Option> {
    private String name;
    private String data;
    private String path;

    public Option(String n, String d, String p) {
        name = n;
        data = d;
        path = p;
    }

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }

    public String getPath() {
        return path;
    }

    @Override
    public int compareTo(Option o) {
        if (this.name != null)
            return this.name.toLowerCase(LocaleUtils.getCurrent()).compareTo(o.getName().toLowerCase(LocaleUtils.getCurrent()));
        else
            throw new IllegalArgumentException();
    }
}