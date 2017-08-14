package com.nuon.peter.demoapp.ui.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.nuon.peter.demoapp.ui.genres.fragment.GenresFragment;
import com.nuon.peter.demoapp.ui.home.fragment.HomeFragment;
import com.nuon.peter.demoapp.ui.movielist.fragment.MovieListFragment;

/**
 * Created by beniten on 12/29/16.
 */

public class MainPagersAdapter extends FragmentPagerAdapter {

  public MainPagersAdapter(FragmentManager fm){
    super(fm);
  }

  @Override public Fragment getItem(int position) {
    if(position == 0) {
      return new HomeFragment();
    } else if (position == 1) {
      return new MovieListFragment();
    } else {
      return new GenresFragment();
    }
  }

  @Override public int getCount() {
    return 3;
  }

  @Override public CharSequence getPageTitle(int position) {
    if(position == 0) {
      return "Group";
    } else if(position == 1) {
      return "Search";
    } else {
      return "Next";
    }
  }
}
