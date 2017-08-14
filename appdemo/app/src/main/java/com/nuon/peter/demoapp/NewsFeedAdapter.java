package com.nuon.peter.demoapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by manithnuon on 4/1/17.
 */

public class NewsFeedAdapter extends RecyclerView.Adapter<NewsFeedAdapter.ViewHolder>{

  private List<FeedModel> newsfeed = new LinkedList<>();

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_demo_exercise_one,parent,false);
    return new ViewHolder(view);
  }

  public void appendDataToList(List<FeedModel> list){
    if (list == null) {
      return;
    }
    newsfeed.addAll(list);
  }

  public void clear(){
    newsfeed.clear();
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    holder.userName.setText(newsfeed.get(position).userName);
    holder.userMusicBackground.setImageResource(newsfeed.get(position).image);
    holder.userDescription.setText(newsfeed.get(position).userDescription);
    holder.loveContainer.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Toast.makeText(view.getContext(), "Love Clicked", Toast.LENGTH_SHORT).show();
      }
    });
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Toast.makeText(view.getContext(), "Whole view Clicked", Toast.LENGTH_SHORT).show();
      }
    });
  }

  @Override public int getItemViewType(int position) {
    return super.getItemViewType(position);
  }

  @Override public int getItemCount() {
    return newsfeed.size() ;
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    public TextView userName;
    public TextView userDescription;
    public ImageView userMusicBackground;
    public LinearLayout loveContainer;

    public ViewHolder(View itemView) {
      super(itemView);
      userName = (TextView) itemView.findViewById(R.id.tv_user);
      userDescription = (TextView) itemView.findViewById(R.id.tv_description);
      userMusicBackground = (ImageView) itemView.findViewById(R.id.iv_image_music);
      loveContainer = (LinearLayout) itemView.findViewById(R.id.love_container);
    }
  }


}
