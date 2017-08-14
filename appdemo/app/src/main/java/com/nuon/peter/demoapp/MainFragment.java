package com.nuon.peter.demoapp;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by manithnuon on 4/22/17.
 */

public class MainFragment extends Fragment {

  @BindView(R.id.bottom_navigation) BottomNavigationView bottomNavigationView;
  @BindView(R.id.viewpager) ViewPager pager;
  private DemoActivityWithFragment activity;
  private MainFragmentPagerAdapter adapter;
  private MenuItem prevMenuItem;

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    this.activity = (DemoActivityWithFragment) context;
  }


  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    /*activity.getWindow().getDecorView().setSystemUiVisibility(
        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    activity.getWindow().setStatusBarColor(Color.TRANSPARENT);

    activity.getWindow().getDecorView().setSystemUiVisibility(
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_IMMERSIVE);*/
    View view = inflater.inflate(R.layout.layout_main_ui_bottom_navigation,container,false);
    ButterKnife.bind(this,view);
    BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
    return view;

  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    adapter = new MainFragmentPagerAdapter(activity.getSupportFragmentManager());
    pager.setAdapter(adapter);
    pager.setOffscreenPageLimit(adapter.getCount());
    bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
      @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
          case R.id.group:
            pager.setCurrentItem(0);
            break;
          case R.id.seach:
            pager.setCurrentItem(1);
            break;
          case R.id.profile:
            pager.setCurrentItem(2);
            break;
          /*case R.id.watch:
            pager.setCurrentItem(3);
            break;*/
        }
        return false;
      }
    });
    pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
      }
      @Override
      public void onPageSelected(int position) {
        if (prevMenuItem != null) {
          prevMenuItem.setChecked(false);
        } else {
          bottomNavigationView.getMenu().getItem(0).setChecked(false);
        }
        bottomNavigationView.getMenu().getItem(position).setChecked(true);
        prevMenuItem = bottomNavigationView.getMenu().getItem(position);
      }
      @Override
      public void onPageScrollStateChanged(int state) {

      }
    });
  }
}
