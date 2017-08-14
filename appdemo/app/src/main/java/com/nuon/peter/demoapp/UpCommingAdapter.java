package com.nuon.peter.demoapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by beniten on 12/27/16.
 */

public class UpCommingAdapter extends RecyclerView.Adapter<UpCommingAdapter.ViewHolder> {

  private Context context;
  private List<MovieItem> moviesItems = new LinkedList<>();

  public UpCommingAdapter(Context context){
    this.context = context;
  }

  @Override public UpCommingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_home_fragment_recycler_item,parent,false);
    return new UpCommingAdapter.ViewHolder(view);
  }

  @Override public void onBindViewHolder(UpCommingAdapter.ViewHolder holder, int position) {
    holder.title.setText(moviesItems.get(position).title);
    Glide.with(context).load(moviesItems.get(position).url).centerCrop().crossFade().diskCacheStrategy(
            DiskCacheStrategy.RESULT).into(holder.bgMovie);
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

    @BindView(R.id.title) public TextView title;
    @BindView(R.id.bg_movie) public ImageView bgMovie;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this,itemView);
    }
  }

}
