package com.nuon.peter.demoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class NextActivity extends AppCompatActivity {


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_equally_width);
    getSupportActionBar().setElevation(0);

    //String data = getIntent().getExtras().getString("data");
    //Toast.makeText(this, data, Toast.LENGTH_SHORT).show();

    //Get FeedModel
    FeedModel data = (FeedModel) getIntent().getSerializableExtra("data");
    Log.d("Data::::",data.toString());

  }

}
