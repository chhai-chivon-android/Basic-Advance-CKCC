package com.nuon.peter.demoapp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by beniten on 12/29/16.
 */

public class FeedModel implements Serializable {

  public String userDescription;
  public String userName;
  public int image;
  public int type;

  public List<TopModel> topModels;

  public FeedModel(String userName,String userDescription,int image) {
    this.userDescription = userDescription;
    this.userName = userName;
    this.image = image;
  }

  public FeedModel(String userDescription, String userName, int image, int type) {
    this.userDescription = userDescription;
    this.userName = userName;
    this.image = image;
    this.type = type;
  }

  public FeedModel(String userDescription, String userName, int image, int type,
      List<TopModel> topModels) {
    this.userDescription = userDescription;
    this.userName = userName;
    this.image = image;
    this.type = type;
    this.topModels = topModels;
  }

  @Override public String toString() {
    return "FeedModel{" +
        "userDescription='" + userDescription + '\'' +
        ", userName='" + userName + '\'' +
        ", image=" + image +
        '}';
  }
}
