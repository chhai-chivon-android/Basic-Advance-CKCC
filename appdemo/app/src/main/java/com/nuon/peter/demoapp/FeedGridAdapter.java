package com.nuon.peter.demoapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

/**
 * Created by beniten on 12/27/16.
 */

public class FeedGridAdapter extends RecyclerView.Adapter<FeedGridAdapter.ViewHolder> {

  private Context context;
  private List<FeedModel> feeds;


  public FeedGridAdapter(List<FeedModel> feeds, Context context){
    this.context = context;
    this.feeds = feeds;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_demo_gride,parent,false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    holder.userName.setText(feeds.get(position).userName);
    holder.userDescription.setText(feeds.get(position).userDescription);
    holder.userMusicBackground.setImageResource(feeds.get(position).image);
  }

  @Override public int getItemViewType(int position) {
    return super.getItemViewType(position);
  }

  @Override public int getItemCount() { return feeds.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    public TextView userName;
    public TextView userDescription;
    public ImageView userMusicBackground;

    public ViewHolder(View itemView) {
      super(itemView);
      userName = (TextView) itemView.findViewById(R.id.tv_user);
      userDescription = (TextView) itemView.findViewById(R.id.tv_description);
      userMusicBackground = (ImageView) itemView.findViewById(R.id.iv_image_music);

    }
  }

}
