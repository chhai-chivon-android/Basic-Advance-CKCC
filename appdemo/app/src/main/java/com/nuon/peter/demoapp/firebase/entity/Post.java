package com.nuon.peter.demoapp.firebase.entity;

/**
 * Created by manithnuon on 6/22/17.
 */

public class Post {

    public Post() {}

    public People people;
    public String url;
    public Object timestamp;
    public String title;
    public String description;

    public Post(People people, String url, Object timestamp, String title, String description) {
        this.people = people;
        this.url = url;
        this.timestamp = timestamp;
        this.title = title;
        this.description = description;
    }
}
