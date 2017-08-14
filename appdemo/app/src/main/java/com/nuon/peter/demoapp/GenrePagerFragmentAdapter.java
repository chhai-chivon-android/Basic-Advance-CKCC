package com.nuon.peter.demoapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.List;

/**
 * Created by manithnuon on 4/21/17.
 */

public class GenrePagerFragmentAdapter extends FragmentStatePagerAdapter {
  private List<GenreItem> genres;
  public GenrePagerFragmentAdapter(FragmentManager fm, List<GenreItem> genres) {
    super(fm);
    this.genres = genres;
  }

  @Override public CharSequence getPageTitle(int position) {
    return null != genres ? genres.get(position).title : null;
  }

  @Override public Fragment getItem(int position) {
    return new MovieFragment();
  }

  @Override public int getCount() {
    return genres.size();
  }
}
