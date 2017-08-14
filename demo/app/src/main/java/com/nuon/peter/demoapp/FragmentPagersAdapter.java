package com.nuon.peter.demoapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by beniten on 12/29/16.
 */

public class FragmentPagersAdapter extends FragmentPagerAdapter {

  public FragmentPagersAdapter(FragmentManager fm){
    super(fm);
  }

  @Override public Fragment getItem(int position) {
    if(position == 0){
      return new FragmentOne();
    }else{
      return new FragmentTwo();
    }
  }

  @Override public int getCount() {
    return 2;
  }

  @Override public CharSequence getPageTitle(int position) {
    if(position == 0) return "Home";
    else return "Next";
  }
}
