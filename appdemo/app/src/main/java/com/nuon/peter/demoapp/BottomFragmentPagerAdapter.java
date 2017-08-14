package com.nuon.peter.demoapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.List;

/**
 * Created by manithnuon on 4/29/17.
 */

public class BottomFragmentPagerAdapter extends FragmentStatePagerAdapter {
  private List<GenreItem> genres;
  public BottomFragmentPagerAdapter(FragmentManager fm, List<GenreItem> genres) {
    super(fm);
    this.genres = genres;
  }

  @Override public CharSequence getPageTitle(int position) {
    return null != genres ? genres.get(position).title : null;
  }

  @Override public Fragment getItem(int position) {
    switch (position){
      case 0 : return new FamilyFragment();
      case 1 : return new ComedyFragment();
      case 2 : return new ActionFragment();
      case 3 : return new HorrorFragment();
      default: return new AdventureFragment();
    }
  }

  @Override public int getCount() {
    return genres.size();
  }
}
