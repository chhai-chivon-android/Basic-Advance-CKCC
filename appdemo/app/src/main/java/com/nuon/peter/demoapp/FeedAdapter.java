package com.nuon.peter.demoapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by beniten on 12/27/16.
 */

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private Context context;
  private static final int TYPE_ONE = 0;
  private static final int TYPE_TWO = 1;
  private List<FeedModel> feeds = new LinkedList<>();

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType == 1) {
      View view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.layout_demo_exercise_one, parent, false);
      return new NormalViewHolder(view);
    } else {
      View view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.layout_demo_exercise_one_item, parent, false);
      return new TopViewHolder(view);
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
    } else if (holder instanceof TopViewHolder) {
      ((TopViewHolder) holder).userDescription.setText(feeds.get(position).userDescription);
      ((TopViewHolder) holder).userMusicBackground.setImageResource(feeds.get(position).image);
    }
  }

  @Override public int getItemViewType(int position) {
    if(feeds.get(position).type == 1){
      return TYPE_ONE;
    }else{
      return TYPE_TWO;
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
}
