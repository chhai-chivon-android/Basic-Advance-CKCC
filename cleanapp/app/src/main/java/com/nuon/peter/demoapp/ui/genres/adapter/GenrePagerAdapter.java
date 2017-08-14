package com.nuon.peter.demoapp.ui.genres.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.nuon.peter.demoapp.ui.genres.entity.genre.GenresItem;
import com.nuon.peter.demoapp.ui.genres.fragment.GenresMoviesFragment;
import com.nuon.peter.demoapp.ui.home.adapter.SmartFragmentStatePagerAdapter;
import java.util.List;

/**
 * Created by manithnuon on 2/11/17.
 */

public class GenrePagerAdapter extends SmartFragmentStatePagerAdapter {

  private List<GenresItem> mCategories;

  public GenrePagerAdapter(FragmentManager fragmentManager,List<GenresItem> mCategories) {
    super(fragmentManager);
    this.mCategories = mCategories;
  }

  public GenresItem getCategoryPosition(int position){
    return mCategories.get(position);
  }

  @Override public Fragment getItem(int position) {
    return new GenresMoviesFragment();
  }

  @Override public int getCount() {
    return null != mCategories ? mCategories.size() : 0;
  }

  @Override public CharSequence getPageTitle(int position) {
    return null != mCategories ? mCategories.get(position).getName() : null;
  }
}
