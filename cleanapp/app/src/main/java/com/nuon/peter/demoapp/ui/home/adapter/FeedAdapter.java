package com.nuon.peter.demoapp.ui.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.nuon.peter.demoapp.R;
import com.nuon.peter.demoapp.ui.home.entity.FeedModelNetwork;
import java.util.List;

/**
 * Created by beniten on 12/27/16.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

  private Context context;
  private List<FeedModelNetwork> feeds;

  public FeedAdapter(List<FeedModelNetwork> feeds, Context context){
    this.feeds = feeds;
    this.context = context;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_demo_exercise_one,parent,false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    holder.userName.setText(feeds.get(position).userName);
    holder.userDescription.setText(feeds.get(position).userDescription);
    Glide.with(context).load(feeds.get(position).url).into(holder.userMusicBackground);
    //holder.userMusicBackground.setImageResource(feeds.get(position).image);
  }

  @Override public int getItemCount() { return feeds.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_user) public TextView userName;
    @BindView(R.id.tv_description) public TextView userDescription;
    @BindView(R.id.iv_image_music) public ImageView userMusicBackground;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this,itemView);
      /*userName = (TextView) itemView.findViewById(R.id.tv_user);
      userDescription = (TextView) itemView.findViewById(R.id.tv_description);
      userMusicBackground = (ImageView) itemView.findViewById(R.id.iv_image_music);*/
    }
  }

}
