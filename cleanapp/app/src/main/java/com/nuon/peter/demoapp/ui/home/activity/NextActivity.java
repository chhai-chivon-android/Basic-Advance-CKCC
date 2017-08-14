package com.nuon.peter.demoapp.ui.home.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.nuon.peter.demoapp.R;
import com.nuon.peter.demoapp.ui.home.entity.FeedModel;

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

    /*tab = (TabLayout) findViewById(R.id.tablayout);
    tab.setTabTextColors(ContextCompat.getColor(getBaseContext(),R.color.grey),ContextCompat.getColor(getBaseContext(),R.color.white));
    tab.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"));
    tab.setSelectedTabIndicatorHeight(10);
    pager = (ViewPager) findViewById(R.id.pager);
    FragmentManager manager = getSupportFragmentManager();
    adapter = new FragmentPagersAdapter(manager);
    pager.setAdapter(adapter);
    tab.setupWithViewPager(pager);*/

  }

}
