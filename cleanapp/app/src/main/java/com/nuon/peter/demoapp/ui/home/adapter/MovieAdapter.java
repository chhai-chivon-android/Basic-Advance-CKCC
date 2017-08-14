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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nuon.peter.demoapp.R;

import com.nuon.peter.demoapp.ui.movielist.api.MovieListAPI;
import com.nuon.peter.demoapp.ui.movielist.entity.ResultsItem;
import com.nuon.peter.demoapp.utils.views.slantextview.SlantedTextView;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by beniten on 12/27/16.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

  private Context context;
  private List<ResultsItem> moviesItems = new LinkedList<>();

  public MovieAdapter(Context context){
    this.context = context;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_demo_movie_horizotal,parent,false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    holder.title.setText(moviesItems.get(position).getTitle());
    holder.rating.setText(Double.toString(moviesItems.get(position).getVoteAverage()));
    Glide.with(context).load(MovieListAPI.getImageURL("w320")+moviesItems.get(position)
            .getPosterPath())
            .centerCrop()
            .crossFade()
            .diskCacheStrategy(DiskCacheStrategy.RESULT)
            .into(holder.bgMovie);
    //holder.userMusicBackground.setImageResource(feeds.get(position).image);
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
    @BindView(R.id.title) public TextView title;
    @BindView(R.id.bg_movie) public ImageView bgMovie;
    @BindView(R.id.rating) public SlantedTextView rating;
    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this,itemView);
    }
  }

}
