package com.nuon.peter.demoapp.rest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.nuon.peter.demoapp.BottomFragment;
import com.nuon.peter.demoapp.FimListFragment;
import com.nuon.peter.demoapp.HomeFragment;
import com.nuon.peter.demoapp.rest.adapter.MainBindingFragment;

/**
 * Created by manithnuon on 5/6/17.
 */

public class RestPagerAdapter extends FragmentStatePagerAdapter {
  public RestPagerAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override public Fragment getItem(int position) {
    if (position == 0) {
      return new MainBindingFragment();
    } else if (position == 1) {
      return new GenresRestFragment();
    } else if (position == 2) {
      return new FilmRestBindFragment();
    } else {
      return new SocialFragment();
    }
  }

  @Override public int getCount() {
    return 4;
  }
}
