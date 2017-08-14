package com.nuon.peter.demoapp.rest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.nuon.peter.demoapp.AppPagerAdapter;
import com.nuon.peter.demoapp.BottomNavigationViewHelper;
import com.nuon.peter.demoapp.DemoActivityWithFragment;
import com.nuon.peter.demoapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by manithnuon on 5/6/17.
 */

public class RestFragment extends Fragment {

  private RestActivityWithFragment activity;
  private RestPagerAdapter adapter;
  @BindView(R.id.bottom_navigation) BottomNavigationView bottomNavigationView;
  @BindView(R.id.viewpager) ViewPager pager;

  private MenuItem prevMenuItem;


  @Override public void onAttach(Context context) {
    super.onAttach(context);
    this.activity = (RestActivityWithFragment) context;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.layout_main_ui_bottom_navigation,container,false);
    ButterKnife.bind(this,view);
    return view;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    adapter = new RestPagerAdapter(getFragmentManager());
    pager.setAdapter(adapter);
    pager.setOffscreenPageLimit(adapter.getCount());
    BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
    bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
      @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.group){
          pager.setCurrentItem(0);
        } else if(item.getItemId() == R.id.seach){
          pager.setCurrentItem(1);
        } else if(item.getItemId() == R.id.profile){
          pager.setCurrentItem(2);
        } else {
          pager.setCurrentItem(3);
        }
        return false;
      }
    });

    pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override public void onPageSelected(int position) {
        if(prevMenuItem != null){
          prevMenuItem.setChecked(false);
        } else {
          bottomNavigationView.getMenu().getItem(0).setChecked(false);
        }
        bottomNavigationView.getMenu().getItem(position).setChecked(true);
        prevMenuItem = bottomNavigationView.getMenu().getItem(position);

      }

      @Override public void onPageScrollStateChanged(int state) {

      }
    });
  }
}
