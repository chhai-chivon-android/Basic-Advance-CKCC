package com.nuon.peter.demoapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.github.pwittchen.prefser.library.Prefser;

/**
 * Created by manithnuon on 4/21/17.
 */

public class DemoActivityWithFragment extends AppCompatActivity {
  Prefser prefser;
  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_activity_with_fragment);
    prefser = new Prefser(this);
    getSupportFragmentManager().beginTransaction()
        .add(R.id.container, new AppFragment())
        .commit();
  }
}
