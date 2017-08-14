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

public class FeedAdapterWithType extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private Context context;
  private List<FeedModelWithType> feeds;
  private static final int TYPE_ONE = 0;
  private static final int TYPE_TWO = 1;


  public FeedAdapterWithType(List<FeedModelWithType> feeds, Context context){
    this.context = context;
    this.feeds = feeds;
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if(viewType == TYPE_ONE){
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_demo_ui_one_homework_two,parent,false);
      return new TypeOneViewHolder(view);
    }else{
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_demo_ui_two_homework_two,parent,false);
      return new TypeTwoViewHolder(view);
    }

  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof  TypeOneViewHolder) {
      ((TypeOneViewHolder) holder).userName.setText(feeds.get(position).userName);
      ((TypeOneViewHolder) holder).userDescription.setText(feeds.get(position).userDescription);
      ((TypeOneViewHolder) holder).userMusicBackground.setImageResource(feeds.get(position).image);
      ((TypeOneViewHolder) holder).userMusicBackground.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {

        }
      });
    }else if (holder instanceof  TypeTwoViewHolder){
      ((TypeTwoViewHolder) holder).userNameType.setText(feeds.get(position).userName);
      ((TypeTwoViewHolder) holder).userDescriptionType.setText(feeds.get(position).userDescription);
      ((TypeTwoViewHolder) holder).userMusicBackgroundType.setImageResource(feeds.get(position).image);
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

  public static class TypeOneViewHolder extends RecyclerView.ViewHolder {
    public TextView userName;
    public TextView userDescription;
    public ImageView userMusicBackground;

    public TypeOneViewHolder(View itemView) {
      super(itemView);
      userName = (TextView) itemView.findViewById(R.id.tv_user);
      userDescription = (TextView) itemView.findViewById(R.id.tv_description);
      userMusicBackground = (ImageView) itemView.findViewById(R.id.iv_image_music);

    }
  }

  public static class TypeTwoViewHolder extends RecyclerView.ViewHolder {
    public TextView userNameType;
    public TextView userDescriptionType;
    public ImageView userMusicBackgroundType;

    public TypeTwoViewHolder(View itemView) {
      super(itemView);
      userNameType = (TextView) itemView.findViewById(R.id.tv_user_type);
      userDescriptionType = (TextView) itemView.findViewById(R.id.tv_description_type);
      userMusicBackgroundType = (ImageView) itemView.findViewById(R.id.iv_image_music_type);

    }
  }

}
