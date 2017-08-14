package com.nuon.peter.demoapp.firebase.entity;

/**
 * Created by manithnuon on 6/21/17.
 */

public class People {

    public String email;
    public String url;
    public String name;
    public String uuid;

    public People() {}

    public People(String email, String url, String name, String uuid) {
        this.email = email;
        this.url = url;
        this.name = name;
        this.uuid = uuid;
    }



    public People(String email, String url, String name) {
        this.email = email;
        this.url = url;
        this.name = name;
    }

    public People(String email, String url) {
        this.email = email;
        this.url = url;
    }

    public People(String email) {
        this.email = email;
    }
}
