package com.nuon.peter.demoapp.firebase.entity;

/**
 * Created by manithnuon on 6/23/17.
 */

public class Comment {

    public String description;
    public Object timestamp;
    public People people;

    public Comment(){}

    public Comment(String description, Object timestamp, People people) {
        this.description = description;
        this.timestamp = timestamp;
        this.people = people;
    }
}
