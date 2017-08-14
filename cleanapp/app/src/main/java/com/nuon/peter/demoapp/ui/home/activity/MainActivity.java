package com.nuon.peter.demoapp.ui.home.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.nuon.peter.demoapp.R;
import com.nuon.peter.demoapp.ui.home.entity.FeedModel;
import com.nuon.peter.demoapp.ui.home.entity.FeedModelNetwork;
import com.nuon.peter.demoapp.ui.home.entity.FeedModelWithType;
import com.nuon.peter.demoapp.ui.home.fragment.HomeFragment;
import com.nuon.peter.demoapp.ui.home.adapter.StaggerGridAdapter;
import com.nuon.peter.demoapp.ui.home.adapter.FeedAdapter;
import com.nuon.peter.demoapp.ui.home.adapter.FeedAdapterWithType;
import com.nuon.peter.demoapp.ui.home.adapter.FeedGridAdapter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.feed_recyclerview) RecyclerView feedRecyclerview;
  private FeedAdapter adapter;
  private List<FeedModel> feeds;
  private List<FeedModelNetwork> feedModelNetworks;
  private List<FeedModelWithType> feedModelWithTypes;
  private FeedGridAdapter gridAdapter;
  private StaggerGridAdapter staggerGridAdapter;
  private FeedAdapterWithType typeAdapter;
  private HomeFragment fragment;
  public static final String FRAGMENT_MAIN_HOME = "FRAGMENT_MAIN_HOME";


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_demo_recyclerview);
    ButterKnife.bind(this);
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

    //feedRecyclerview = (RecyclerView) findViewById(R.id.feed_recyclerview);

    //LinearLayoutManager Vertical
    /*feeds = new ArrayList<>();
    feedRecyclerview.setLayoutManager(new LinearLayoutManager(getBaseContext()));
    adapter = new FeedAdapter(getDummyDatas());
    feedRecyclerview.setAdapter(adapter);*/

    //LinearLayoutManager Horizontal
    feedModelNetworks = new ArrayList<>();
    feedRecyclerview.setLayoutManager(new LinearLayoutManager(getBaseContext()));
    feedRecyclerview.setHasFixedSize(true);
    adapter = new FeedAdapter(getDummyDataNetwork(),getBaseContext());
    feedRecyclerview.setAdapter(adapter);

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


    /*fragment = new FragmentOne();
    getSupportFragmentManager().beginTransaction()
        .add(R.id.linear,fragment, MainActivity.FRAGMENT_MAIN_HOME)
        .commit();*/

    //Homework
    /*feedModelWithTypes = new ArrayList<>();
    feedRecyclerview.setLayoutManager(new LinearLayoutManager(getBaseContext()));
    typeAdapter = new FeedAdapterWithType(getDummyDataWithType(),getBaseContext());
    feedRecyclerview.setAdapter(typeAdapter);*/

  }

  private void openBrowser(String url){
    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setData(Uri.parse(url));
    startActivity(intent);
  }

  private void dialContactPhone(final String phoneNumber) {
    startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
  }

  public void toastMe(String toast){
    Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
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
  private List<FeedModelNetwork> getDummyDataNetwork(){
    feedModelNetworks.add(new FeedModelNetwork("Lina chan","I'm getting hot now. come baby and bring some water","https://st.hzcdn.com/fimgs/9351dd1300226ca7_1944-w500-h666-b0-p0--traditional-landscape.jpg"));
    feedModelNetworks.add(new FeedModelNetwork("Nania Queen","Wow that cool bro","https://st.hzcdn.com/fimgs/c221d83705e84ec8_2899-w500-h666-b0-p0--traditional-landscape.jpg"));
    feedModelNetworks.add(new FeedModelNetwork("Peter John","It's going better and better","https://st.hzcdn.com/fimgs/d291b0d802716d8f_2857-w500-h666-b0-p0--traditional-landscape.jpg"));
    feedModelNetworks.add(new FeedModelNetwork("John Doe","Are you sure ? It's getting started.","https://st.hzcdn.com/fimgs/cb51723907d145c0_9951-w500-h666-b0-p0--traditional-landscape.jpg"));
    feedModelNetworks.add(new FeedModelNetwork("Anna Kentrik","Please give me one more night","https://st.hzcdn.com/fimgs/0651c6e005248910_0894-w500-h666-b0-p0--contemporary-landscape.jpg"));
    feedModelNetworks.add(new FeedModelNetwork("Addam Ken","It's was not going to be that easy","https://st.hzcdn.com/fimgs/f8118b7006ee6d82_5028-w500-h666-b0-p0--contemporary-landscape.jpg"));
    return feedModelNetworks;
  }

  private List<FeedModelWithType> getDummyDataWithType(){
    feedModelWithTypes.add(new FeedModelWithType("Dara chan","I'm getting hot now",R.mipmap.song_1,1));
    feedModelWithTypes.add(new FeedModelWithType("Nania Queen","Wow that cool bro",R.mipmap.song_2,1));
    feedModelWithTypes.add(new FeedModelWithType("Peter John","It's going better and better",R.mipmap.song_3,2));
    feedModelWithTypes.add(new FeedModelWithType("John Doe","Are you sure ? It's getting started.",R.mipmap.song_4,2));
    feedModelWithTypes.add(new FeedModelWithType("Anna Kentrik","Please give me one more night",R.mipmap.song_5,2));
    feedModelWithTypes.add(new FeedModelWithType("Addam Ken","It's was not going to be that easy",R.mipmap.song_6,1));
    return feedModelWithTypes;
  }
}
