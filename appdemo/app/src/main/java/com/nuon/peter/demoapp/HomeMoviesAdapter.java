package com.nuon.peter.demoapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by beniten on 12/27/16.
 */

public class HomeMoviesAdapter extends RecyclerView.Adapter<HomeMoviesAdapter.ViewHolder> {

  private List<MovieItem> moviesItems = new LinkedList<>();
  private Context context;

  public HomeMoviesAdapter(Context context){
    this.context = context;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_home_fragment_recycler_item,parent,false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(final ViewHolder holder, int position) {
    holder.title.setText(moviesItems.get(position).title);
    /*Glide.with(context).load(moviesItems.get(position).url)
            .thumbnail(Glide.with(context).load(moviesItems.get(position).thumbnail).bitmapTransform(new BlurTransformation(context,25)))
            .dontAnimate().into(holder.bgMovie);*/

    /*Glide.with(context).load(moviesItems.get(position).url).asBitmap().into(new SimpleTarget<Bitmap>() {
      @Override
      public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
        holder.bgMovie.setImageBitmap(resource);
      }
    });*/

    Picasso.with(context).load(moviesItems.get(position).url).into(holder.bgMovie);

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
