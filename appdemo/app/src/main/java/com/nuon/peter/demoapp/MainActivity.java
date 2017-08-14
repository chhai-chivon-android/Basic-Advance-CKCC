package com.nuon.peter.demoapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private RecyclerView feedRecyclerview;
  private FeedAdapter adapter;
  private List<FeedModel> feeds;
  private FeedGridAdapter gridAdapter;
  private StaggerGridAdapter staggerGridAdapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_demo_recyclerview);

    /*TextView signin = (TextView) findViewById(R.id.sign_btn);
    signin.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Log.d("Clicked:::","I was clicked");
        Intent intent = new Intent(getBaseContext(), NextActivity.class);

        //Pass String data
        //intent.putExtra("data","I am your data");
        //startActivity(intent);

        //Pass Object
        //intent.putExtra("data",new FeedModel("Lina chan","I'm getting hot now",R.mipmap.song_1));
        //startActivity(intent);

        //dialContactPhone("+85577649455");
        //openBrowser("https://www.facebook.com");
      }
    });*/

    feedRecyclerview = (RecyclerView) findViewById(R.id.feed_recyclerview);

    //LinearLayoutManager Vertical
    feeds = new ArrayList<>();
    feedRecyclerview.setLayoutManager(new LinearLayoutManager(getBaseContext()));
    adapter = new FeedAdapter();
    feedRecyclerview.setAdapter(adapter);

    //LinearLayoutManager Horizontal
    /*feeds = new ArrayList<>();
    feedRecyclerview.setLayoutManager(new LinearLayoutManager(getBaseContext(),LinearLayoutManager.HORIZONTAL,false));
    adapter = new FeedAdapter(getDummyDatas());
    feedRecyclerview.setAdapter(adapter);*/

    //GridLayoutManager
    /*feeds = new ArrayList<>();
    feedRecyclerview.setLayoutManager(new GridLayoutManager(getBaseContext(),2));
    gridAdapter = new FeedGridAdapter(getDummyDatas(),getBaseContext());
    feedRecyclerview.setAdapter(gridAdapter);*/

    //StaggeredGrid
    /*feeds = new ArrayList<>();
    StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,
                               StaggeredGridLayoutManager.VERTICAL);
    feedRecyclerview.setLayoutManager(manager);
    staggerGridAdapter = new StaggerGridAdapter(getDummyDatas());
    feedRecyclerview.setAdapter(staggerGridAdapter);*/

  }

  private void openBrowser(String url){
    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setData(Uri.parse(url));
    startActivity(intent);
  }

  private void dialContactPhone(final String phoneNumber) {
    startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
  }

  private List<FeedModel> getDummyDatas(){
    feeds.add(new FeedModel("Lina chan","I'm getting hot now. come baby and bring some water",R.mipmap.song_1));
    feeds.add(new FeedModel("Nania Queen","Wow that cool bro",R.mipmap.song_2));
    feeds.add(new FeedModel("Peter John","It's going better and better",R.mipmap.song_3));
    feeds.add(new FeedModel("John Doe","Are you sure ? It's getting started.",R.mipmap.song_4));
    feeds.add(new FeedModel("Anna Kentrik","Please give me one more night",R.mipmap.song_5));
    feeds.add(new FeedModel("Addam Ken","It's was not going to be that easy",R.mipmap.song_6));
    return feeds;
  }
}
