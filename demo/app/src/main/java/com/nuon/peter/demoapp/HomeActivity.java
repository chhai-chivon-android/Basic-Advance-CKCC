package com.nuon.peter.demoapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by manithnuon on 1/21/17.
 */

public class HomeActivity extends AppCompatActivity {
  @BindView(R.id.tablayout) TabLayout tab;
  @BindView(R.id.pager) ViewPager pager;
  private FragmentPagersAdapter adapter;
  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_demo_tab_viewger_fragment);
    getSupportActionBar().setElevation(0);
    ButterKnife.bind(this);

    tab.setTabTextColors(ContextCompat.getColor(getBaseContext(),R.color.grey),ContextCompat.getColor(getBaseContext(),R.color.white));
    tab.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"));
    tab.setSelectedTabIndicatorHeight(10);
    pager = (ViewPager) findViewById(R.id.pager);
    FragmentManager manager = getSupportFragmentManager();
    adapter = new FragmentPagersAdapter(manager);
    pager.setAdapter(adapter);
    tab.setupWithViewPager(pager);
  }

  public void toastMe(String toast){
    Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
  }
}
