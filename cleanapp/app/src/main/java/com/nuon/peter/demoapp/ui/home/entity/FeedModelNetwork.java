package com.nuon.peter.demoapp.ui.home.entity;

/**
 * Created by manithnuon on 1/21/17.
 */

public class FeedModelNetwork {
  public String userDescription;
  public String userName;
  public String url;

  public FeedModelNetwork(String userDescription, String userName, String url) {
    this.userDescription = userDescription;
    this.userName = userName;
    this.url = url;
  }

  @Override public String toString() {
    return "FeelModelNetwork{" +
        "userDescription='" + userDescription + '\'' +
        ", userName='" + userName + '\'' +
        ", url='" + url + '\'' +
        '}';
  }
}
