package com.nuon.peter.demoapp.rest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.nuon.peter.demoapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by manithnuon on 5/6/17.
 */

public class RestTabFragment extends Fragment {

  private RestActivityWithFragment activity;
  private RestPagerAdapter adapter;
  @BindView(R.id.bottom_tab) TabLayout tabLayout;
  @BindView(R.id.viewpager) ViewPager pager;
  private MenuItem prevMenuItem;


  @Override public void onAttach(Context context) {
    super.onAttach(context);
    this.activity = (RestActivityWithFragment) context;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.layout_activity_tablayout,container,false);
    ButterKnife.bind(this,view);
    return view;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    adapter = new RestPagerAdapter(getFragmentManager());
    pager.setAdapter(adapter);
    pager.setOffscreenPageLimit(adapter.getCount());
    tabLayout.setupWithViewPager(pager);
    tabLayout.getTabAt(0).setIcon(R.mipmap.ic_mask).setText("Movie");
    tabLayout.getTabAt(1).setIcon(R.mipmap.ic_film).setText("Genres");
    tabLayout.getTabAt(2).setIcon(R.drawable.ic_movie_filter_black_24dp).setText("Film");

    pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override public void onPageSelected(int position) {


      }

      @Override public void onPageScrollStateChanged(int state) {

      }
    });

    /*pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override public void onPageSelected(int position) {
        if (prevMenuItem != null) {
          prevMenuItem.setChecked(false);
        } else {
          bottomNavigationView.getMenu().getItem(0).setChecked(false);
        }
        bottomNavigationView.getMenu().getItem(position).setChecked(true);
        prevMenuItem = bottomNavigationView.getMenu().getItem(position);
      }

      @Override public void onPageScrollStateChanged(int state) {

      }
    });*/
  }
}
