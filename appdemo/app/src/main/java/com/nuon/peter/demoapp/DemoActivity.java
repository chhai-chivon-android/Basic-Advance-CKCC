package com.nuon.peter.demoapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by manithnuon on 4/1/17.
 */

public class DemoActivity extends AppCompatActivity {
  private RecyclerView recyclerView;
  private LinearLayout containerLoading;
  private MultiFeedAdapter adapter;
  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_demo_recyclerviews);
    recyclerView = (RecyclerView) findViewById(R.id.demo_recyclerview);
    containerLoading = (LinearLayout) findViewById(R.id.container_loading);

    //Create LayoutManager for RecyclerView

    //Set LayoutManager to RecyclerView
    recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));


    // Instance NewsFeed Adapter
    adapter = new MultiFeedAdapter(new FeedWithinAdapter());

    //Set Adapter to RecyclerView
    recyclerView.setAdapter(adapter);

    new Handler().postDelayed(new Runnable() {
      @Override public void run() {
        containerLoading.setVisibility(View.GONE);
        // Append Data
        adapter.appendDataToList(getDummyDatasWithinType());
        //Refresh UI after add new data
        adapter.notifyDataSetChanged();
      }
    },1000);

  }

  /*
    Dummy data
   */
  private List<FeedModel> getDummyDatas(){
    List<FeedModel> feeds = new ArrayList<>();
    feeds.add(new FeedModel("Lina chan","I'm getting hot now. come baby and bring some water",R.mipmap.song_1));
    feeds.add(new FeedModel("Nania Queen","Wow that cool bro",R.mipmap.song_2));
    feeds.add(new FeedModel("Peter John","It's going better and better",R.mipmap.song_3));
    feeds.add(new FeedModel("John Doe","Are you sure ? It's getting started.",R.mipmap.song_4));
    feeds.add(new FeedModel("Anna Kentrik","Please give me one more night",R.mipmap.song_5));
    feeds.add(new FeedModel("Addam Ken","It's was not going to be that easy",R.mipmap.song_6));
    return feeds;
  }

  private List<TopModel> getDummyWithinData(){
    List<TopModel> feeds = new ArrayList<>();
    feeds.add(new TopModel("Split","Though Kevin has evidenced 23 personalities to his trusted psychiatrist, Dr. Fletcher, there remains one still submerged who is set to materialize and dominate all the others. Compelled to abduct three teenage girls led by the willful,",R.mipmap.bg_1));
    feeds.add(new TopModel("Guardians of the Galaxy Vol. 2","The Guardians must fight to keep their newfound family together as they unravel the mysteries of Peter Quill's true parentage.",R.mipmap.bg_2));
    feeds.add(new TopModel("Power Rangers","John Wick is forced out of retirement by a former associate looking to seize control of a shadowy international assassins’ guild. Bound by a blood oath to aid him, Wick travels to Rome and does battle against some of the world’s most dangerous killers.",R.mipmap.bg_3));
    feeds.add(new TopModel("The Autopsy of Jane Doe","Father and son coroners who receive a mysterious homicide victim with no apparent cause of death. As they attempt to identify the beautiful young",R.mipmap.bg_4));
    feeds.add(new TopModel("Sleepless","Undercover Las Vegas police officer Vincent Downs is caught in a high stakes web of corrupt cops and the mob-controlled casino underground. When a heist goes wrong, a crew of homicidal gangsters kidnaps Downs’ teenage son.",R.mipmap.bg_5));
    feeds.add(new TopModel("Patriots Day","An account of Boston Police Commissioner Ed Davis's actions in the events leading up to the 2013 Boston Marathon bombing and the aftermath, which includes the city-wide manhunt to find the terrorists behind it.",R.mipmap.bg_6));
    feeds.add(new TopModel("Going in Style","Desperate to pay the bills and come through for their loved ones, three lifelong pals risk it all by embarking on a daring bid to knock off the very bank that absconded with their money.",R.mipmap.bg_7));
    feeds.add(new TopModel("The Bye Bye Man","When three college students move into an old house off campus, they unwittingly unleash a supernatural entity known as The Bye Bye Man, who comes to prey upon them once they discover his name.",R.mipmap.bg_8));
    feeds.add(new TopModel("Aftermath","Based on the airline accident that occurred in July of 2002 and on the events that took place 478 days later.",R.mipmap.bg_9));
    feeds.add(new TopModel("Alien: Covenant","Bound for a remote planet on the far side of the galaxy, the crew of the colony ship Covenant discovers what they think is an uncharted paradise, but is actually a dark, dangerous world — whose sole inhabitant is the “synthetic” David",R.mipmap.bg_10));
    feeds.add(new TopModel("The Promise","Set during the last days of the Ottoman Empire, The Promise follows a love triangle between Michael, a brilliant medical student, the beautiful and sophisticated artist Ana, and Chris - a renowned American journalist based in Paris.",R.mipmap.bg_11));
    return feeds;
  }

  private List<FeedModel> getDummyDatasWithinType(){
    List<FeedModel> feeds = new ArrayList<>();
    feeds.add(new FeedModel("Lina chan","I'm getting hot now. come baby and bring some water",R.mipmap.song_1,0));
    feeds.add(new FeedModel("Nania Queen","Wow that cool bro",R.mipmap.song_2,1));
    feeds.add(new FeedModel("Peter John","It's going better and better",R.mipmap.song_3,2,getDummyWithinData()));
    feeds.add(new FeedModel("John Doe","Are you sure ? It's getting started.",R.mipmap.song_4,0));
    feeds.add(new FeedModel("Anna Kentrik","Please give me one more night",R.mipmap.song_5,0));
    feeds.add(new FeedModel("Addam Ken","It's was not going to be that easy",R.mipmap.song_6,1));
    feeds.add(new FeedModel("Lina chan","I'm getting hot now. come baby and bring some water",R.mipmap.song_1,0));
    feeds.add(new FeedModel("Nania Queen","Wow that cool bro",R.mipmap.song_2,0));
    feeds.add(new FeedModel("John Doe","Are you sure ? It's getting started.",R.mipmap.song_4,0));
    feeds.add(new FeedModel("Addam Ken","It's was not going to be that easy",R.mipmap.song_6,1));
    feeds.add(new FeedModel("Anna Kentrik","Please give me one more night",R.mipmap.song_5,0));
    feeds.add(new FeedModel("Peter John","It's going better and better",R.mipmap.song_3,0));
    return feeds;
  }

  /*
    Dummy data
   */
  private List<FeedModel> getDummyDatasWithType(){
    List<FeedModel> feeds = new ArrayList<>();
    feeds.add(new FeedModel("Lina chan","I'm getting hot now. come baby and bring some water",R.mipmap.song_1,0));
    feeds.add(new FeedModel("Nania Queen","Wow that cool bro",R.mipmap.song_2,0));
    feeds.add(new FeedModel("Peter John","It's going better and better",R.mipmap.song_3,1));
    feeds.add(new FeedModel("John Doe","Are you sure ? It's getting started.",R.mipmap.song_4,0));
    feeds.add(new FeedModel("Anna Kentrik","Please give me one more night",R.mipmap.song_5,1));
    feeds.add(new FeedModel("Addam Ken","It's was not going to be that easy",R.mipmap.song_6,1));
    feeds.add(new FeedModel("Lina chan","I'm getting hot now. come baby and bring some water",R.mipmap.song_1,0));
    feeds.add(new FeedModel("Nania Queen","Wow that cool bro",R.mipmap.song_2,0));
    feeds.add(new FeedModel("John Doe","Are you sure ? It's getting started.",R.mipmap.song_4,0));
    feeds.add(new FeedModel("Addam Ken","It's was not going to be that easy",R.mipmap.song_6,1));
    feeds.add(new FeedModel("Anna Kentrik","Please give me one more night",R.mipmap.song_5,1));
    feeds.add(new FeedModel("Peter John","It's going better and better",R.mipmap.song_3,1));
    return feeds;
  }





}
