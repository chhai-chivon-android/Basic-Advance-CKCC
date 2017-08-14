package com.nuon.peter.demoapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by manithnuon on 5/6/17.
 */

public class AppPagerAdapter extends FragmentStatePagerAdapter {
  public AppPagerAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override public Fragment getItem(int position) {
    if (position == 0) {
      return new HomeFragment();
    } else if (position == 1) {
      return new BottomFragment();
    } else {
      return new FimListFragment();
    }
  }

  @Override public int getCount() {
    return 3;
  }
}
