package com.nuon.peter.demoapp.rest.models;

/**
 * Created by manithnuon on 6/16/17.
 */

public class DataMovieDetail {

    public String description;
    public String bigtitle;
    public String rating;
    public String counting;
    public float ratingbar;
    public String relasedate;
    public String releaseyear;
    public String image;

    public DataMovieDetail(String description, String bigtitle, String rating, String counting, float ratingbar, String relasedate, String releaseyear, String image) {
        this.description = description;
        this.bigtitle = bigtitle;
        this.rating = rating;
        this.counting = counting;
        this.ratingbar = ratingbar;
        this.relasedate = relasedate;
        this.releaseyear = releaseyear;
        this.image = image;
    }
}
