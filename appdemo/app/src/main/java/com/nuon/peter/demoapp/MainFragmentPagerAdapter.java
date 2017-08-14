package com.nuon.peter.demoapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by manithnuon on 4/22/17.
 */

public class MainFragmentPagerAdapter extends FragmentStatePagerAdapter {


  public MainFragmentPagerAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override public Fragment getItem(int position) {
    switch (position){
      case 0: return new HomeFragment();
      case 1: return new BottomFragment();
      case 2: return new FilmFragment();
      default: return new CollectionFragment();
    }
  }

  @Override public int getCount() {
    return 4;
  }
}
