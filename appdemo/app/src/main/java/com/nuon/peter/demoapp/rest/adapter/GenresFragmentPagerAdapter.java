package com.nuon.peter.demoapp.rest.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.nuon.peter.demoapp.rest.fragment.ActionBindingFragment;
import com.nuon.peter.demoapp.rest.fragment.ActionFragment;
import com.nuon.peter.demoapp.rest.fragment.AdventureBindingFragment;
import com.nuon.peter.demoapp.rest.fragment.AdventureFragment;
import com.nuon.peter.demoapp.rest.fragment.ComedyBindingFragment;
import com.nuon.peter.demoapp.rest.fragment.ComedyFragment;
import com.nuon.peter.demoapp.rest.fragment.FamilyBindingFragment;
import com.nuon.peter.demoapp.rest.fragment.FamilyFragment;
import com.nuon.peter.demoapp.rest.fragment.HorrorBindingFragment;
import com.nuon.peter.demoapp.rest.fragment.HorrorFragment;
import com.nuon.peter.demoapp.GenreItem;

import java.util.List;

/**
 * Created by manithnuon on 4/29/17.
 */

public class GenresFragmentPagerAdapter extends FragmentStatePagerAdapter {
  private List<GenreItem> genres;
  public GenresFragmentPagerAdapter(FragmentManager fm, List<GenreItem> genres) {
    super(fm);
    this.genres = genres;
  }

  @Override public CharSequence getPageTitle(int position) {
    return null != genres ? genres.get(position).title : null;
  }

  @Override public Fragment getItem(int position) {
    switch (position){
      case 0 : return new FamilyBindingFragment();
      case 1 : return new ComedyBindingFragment();
      case 2 : return new ActionBindingFragment();
      case 3 : return new HorrorBindingFragment();
      default: return new AdventureBindingFragment();
    }
  }

  @Override public int getCount() {
    return genres.size();
  }
}
