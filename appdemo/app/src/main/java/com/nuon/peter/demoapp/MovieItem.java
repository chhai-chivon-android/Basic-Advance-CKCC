package com.nuon.peter.demoapp;

/**
 * Created by manithnuon on 4/21/17.
 */

public class MovieItem {
  public int image;
  public String title;
  public String url;
  public String thumbnail;

  public MovieItem (String title,int image) {
    this.image = image;
    this.title = title;
  }

  public MovieItem (String title, String url) {
    this.title = title;
    this.url = url;
  }

  public MovieItem(String title, String url, String thumbnail) {
    this.title = title;
    this.url = url;
    this.thumbnail = thumbnail;
  }
}
