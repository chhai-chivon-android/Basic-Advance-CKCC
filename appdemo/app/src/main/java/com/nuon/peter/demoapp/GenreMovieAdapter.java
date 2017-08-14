package com.nuon.peter.demoapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by beniten on 12/27/16.
 */

public class GenreMovieAdapter extends RecyclerView.Adapter<GenreMovieAdapter.ViewHolder> {

  private List<MovieItem> moviesItems = new LinkedList<>();
  private Context context;

  public GenreMovieAdapter(Context context){
    this.context = context;
  }


  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_genres_fragment_recycler_item,parent,false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    holder.title.setText(moviesItems.get(position).title);
    Glide.with(context).load(moviesItems.get(position).url).centerCrop().crossFade().into(holder.bgMovie);
    //holder.bgMovie.setImageResource(moviesItems.get(position).image);
  }

  public void appendToList(List<MovieItem> list) {
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
    public TextView title;
    public ImageView bgMovie;
    public ViewHolder(View itemView) {
      super(itemView);
      bgMovie = (ImageView) itemView.findViewById(R.id.bg_movie);
      title = (TextView) itemView.findViewById(R.id.title);
    }
  }

}
