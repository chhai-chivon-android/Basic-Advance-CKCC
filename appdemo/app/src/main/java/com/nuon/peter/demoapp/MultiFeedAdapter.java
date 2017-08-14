package com.nuon.peter.demoapp;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by beniten on 12/27/16.
 */

public class MultiFeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int TYPE_ONE = 0;
  private static final int TYPE_TWO = 1;
  private static final int TYPE_THREE = 2;
  private List<FeedModel> feeds = new LinkedList<>();
  private FeedWithinAdapter adapter;
  public MultiFeedAdapter(FeedWithinAdapter adapter) {
    this.adapter = adapter;
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType == TYPE_ONE) {
      View view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.layout_demo_exercise_one, parent, false);
      return new NormalViewHolder(view);
    } else if (viewType == TYPE_TWO) {
      View view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.layout_demo_exercise_one_item, parent, false);
      return new TopViewHolder(view);
    } else {
      View view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.layout_demo_recyclerview_within, parent, false);
      return new AdsViewHolder(view);
    }
  }

  public void appendDataToList(List<FeedModel> list){
    if (list == null) {
      return;
    }
    feeds.addAll(list);
  }

  public void clear(){
    feeds.clear();
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof NormalViewHolder) {
      ((NormalViewHolder) holder).userName.setText(feeds.get(position).userName);
      ((NormalViewHolder) holder).userDescription.setText(feeds.get(position).userDescription);
      ((NormalViewHolder) holder).userMusicBackground.setImageResource(feeds.get(position).image);
    }  else if (holder instanceof TopViewHolder) {
      ((TopViewHolder) holder).userDescription.setText(feeds.get(position).userDescription);
      ((TopViewHolder) holder).userMusicBackground.setImageResource(feeds.get(position).image);
    } else if (holder instanceof AdsViewHolder) {
      adapter.appendDataToList(feeds.get(position).topModels);
      ((AdsViewHolder)holder).recyclerView.setAdapter(adapter);
      adapter.notifyDataSetChanged();
    }
  }

  @Override public int getItemViewType(int position) {
    if (feeds.get(position).type == 0) {
      return TYPE_ONE;
    } else if(feeds.get(position).type == 1){
      return TYPE_TWO;
    } else {
      return TYPE_THREE;
    }
  }

  @Override public int getItemCount() {
    return feeds.size();
  }

  public static class NormalViewHolder extends RecyclerView.ViewHolder {
    public TextView userName;
    public TextView userDescription;
    public ImageView userMusicBackground;

    public NormalViewHolder(View itemView) {
      super(itemView);
      userName = (TextView) itemView.findViewById(R.id.tv_user);
      userDescription = (TextView) itemView.findViewById(R.id.tv_description);
      userMusicBackground = (ImageView) itemView.findViewById(R.id.iv_image_music);
    }
  }

  public static class TopViewHolder extends RecyclerView.ViewHolder {
    public TextView userDescription;
    public ImageView userMusicBackground;

    public TopViewHolder(View itemView) {
      super(itemView);
      userDescription = (TextView) itemView.findViewById(R.id.tv_description);
      userMusicBackground = (ImageView) itemView.findViewById(R.id.iv_image_music);
    }
  }

  public static class AdsViewHolder extends RecyclerView.ViewHolder {
    public RecyclerView recyclerView;

    public AdsViewHolder(View itemView) {
      super(itemView);
      recyclerView = (RecyclerView) itemView.findViewById(R.id.feed_recyclerview_within);
      SnapHelper helper = new GravitySnapHelper(Gravity.START);
      helper.attachToRecyclerView(recyclerView);
      recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
    }
  }
}
