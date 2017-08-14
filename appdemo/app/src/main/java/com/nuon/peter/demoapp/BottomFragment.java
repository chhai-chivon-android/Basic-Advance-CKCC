package com.nuon.peter.demoapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by manithnuon on 4/29/17.
 */

public class BottomFragment extends Fragment{
  private TabLayout tab;
  private ViewPager pager;
  private TextView title;
  private BottomFragmentPagerAdapter adapter;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.layout_fragment_genre,container,false);
    tab = (TabLayout) view.findViewById(R.id.smarttab_genres_list);
    pager = (ViewPager) view.findViewById(R.id.viewpager_movies_list);
    title = (TextView) view.findViewById(R.id.toolbar_title_item);
    return view;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    title.setText("Genre");
    adapter = new BottomFragmentPagerAdapter(getFragmentManager(),getGenres());
    pager.setOffscreenPageLimit(getGenres().size());
    pager.setAdapter(adapter);
    pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override public void onPageSelected(int position) {
      }

      @Override public void onPageScrollStateChanged(int state) {

      }
    });
    tab.setupWithViewPager(pager);
    tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override public void onTabSelected(TabLayout.Tab tab) {
      }

      @Override public void onTabUnselected(TabLayout.Tab tab) {

      }

      @Override public void onTabReselected(TabLayout.Tab tab) {

      }
    });

  }

  private List<GenreItem> getGenres(){
    List<GenreItem> genreItems = new ArrayList<>();
    genreItems.add(new GenreItem(1,"Family"));
    genreItems.add(new GenreItem(2,"Comedy"));
    genreItems.add(new GenreItem(3,"Action"));
    genreItems.add(new GenreItem(4,"Horror"));
    genreItems.add(new GenreItem(5,"Adventure"));
    return genreItems;
  }
}
