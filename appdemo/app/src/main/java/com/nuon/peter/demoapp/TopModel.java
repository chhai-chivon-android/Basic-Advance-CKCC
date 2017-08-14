package com.nuon.peter.demoapp;

import java.io.Serializable;

/**
 * Created by beniten on 12/29/16.
 */

public class TopModel{

  public String userDescription;
  public String userName;
  public int image;
  public int type;

  public TopModel(String userName,String userDescription,int image) {
    this.userDescription = userDescription;
    this.userName = userName;
    this.image = image;
  }

  public TopModel(String userDescription, String userName, int image, int type) {
    this.userDescription = userDescription;
    this.userName = userName;
    this.image = image;
    this.type = type;
  }

  @Override public String toString() {
    return "FeedModel{" +
        "userDescription='" + userDescription + '\'' +
        ", userName='" + userName + '\'' +
        ", image=" + image +
        '}';
  }
}
