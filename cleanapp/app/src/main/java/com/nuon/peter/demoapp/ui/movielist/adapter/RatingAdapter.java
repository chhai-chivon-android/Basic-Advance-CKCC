package com.nuon.peter.demoapp.ui.movielist.adapter;

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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nuon.peter.demoapp.R;
import com.nuon.peter.demoapp.ui.movielist.api.MovieListAPI;
import com.nuon.peter.demoapp.ui.movielist.entity.ResultsItem;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by beniten on 12/27/16.
 */

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.ViewHolder> {

  private Context context;
  private List<ResultsItem> moviesItems = new LinkedList<>();

  public RatingAdapter(Context context){
    this.context = context;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_movie_item,parent,false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    holder.title.setText(moviesItems.get(position).getTitle());
    holder.duration.setText(""+moviesItems.get(position).getReleaseDate());
    holder.rate.setText(""+moviesItems.get(position).getVoteAverage());
    Glide.with(context).load(MovieListAPI.getImageURL("w160") + moviesItems.get(position).getPosterPath()).centerCrop().crossFade().diskCacheStrategy(
        DiskCacheStrategy.RESULT).into(holder.bgMovie);
  }

  public void appendToList(List<ResultsItem> list) {
    if (list == null) {
      return;
    }
    moviesItems.addAll(list);
  }

  public void clear() {
    moviesItems.clear();
  }

  @Override public int getItemCount() { return moviesItems.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.movie_title) public TextView title;
    @BindView(R.id.movie_bg) public ImageView bgMovie;
    @BindView(R.id.movie_length) public TextView duration;
    @BindView(R.id.rating_me) public TextView rate;
    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this,itemView);
    }
  }

}
