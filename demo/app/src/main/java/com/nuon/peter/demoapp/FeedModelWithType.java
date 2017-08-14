package com.nuon.peter.demoapp;

/**
 * Created by beniten on 12/29/16.
 */

public class FeedModelWithType {

  public String userDescription;
  public String userName;
  public int image;
  public int type;

  public FeedModelWithType(String userName,String userDescription,int image, int type) {
    this.userDescription = userDescription;
    this.userName = userName;
    this.image = image;
    this.type = type;
  }
}
