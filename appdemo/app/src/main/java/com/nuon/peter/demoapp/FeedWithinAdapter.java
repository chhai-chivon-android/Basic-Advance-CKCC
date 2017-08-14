package com.nuon.peter.demoapp;

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

public class FeedWithinAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


  private List<TopModel> feeds = new LinkedList<>();

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.layout_demo_exercise_one_item_small, parent, false);
    return new WithinHolder(view);
  }

  public void appendDataToList(List<TopModel> list){
    if (list == null) {
      return;
    }
    feeds.addAll(list);
  }

  public void clear(){
    feeds.clear();
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    ((WithinHolder) holder).titleMovie.setText(feeds.get(position).userName);
    ((WithinHolder) holder).userDescription.setText(feeds.get(position).userDescription);
    ((WithinHolder) holder).userMusicBackground.setImageResource(feeds.get(position).image);
  }

  @Override public int getItemCount() {
    return feeds.size();
  }



  public static class WithinHolder extends RecyclerView.ViewHolder {
    public TextView userDescription;
    public ImageView userMusicBackground;
    public TextView titleMovie;

    public WithinHolder(View itemView) {
      super(itemView);
      titleMovie = (TextView) itemView.findViewById(R.id.title_movie);
      userDescription = (TextView) itemView.findViewById(R.id.description_movie);
      userMusicBackground = (ImageView) itemView.findViewById(R.id.image_movie);
    }
  }
}
