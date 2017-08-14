package com.nuon.peter.demoapp.ui.home.entity;

import java.io.Serializable;

/**
 * Created by beniten on 12/29/16.
 */

public class FeedModel implements Serializable {

  public String userDescription;
  public String userName;
  public int image;

  public FeedModel(String userName,String userDescription,int image) {
    this.userDescription = userDescription;
    this.userName = userName;
    this.image = image;
  }

  @Override public String toString() {
    return "FeedModel{" +
        "userDescription='" + userDescription + '\'' +
        ", userName='" + userName + '\'' +
        ", image=" + image +
        '}';
  }
}
