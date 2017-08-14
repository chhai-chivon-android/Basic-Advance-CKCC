package com.nuon.peter.demoapp.rest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nuon.peter.demoapp.MovieItem;
import com.nuon.peter.demoapp.R;
import com.nuon.peter.demoapp.rest.Common;
import com.nuon.peter.demoapp.rest.MovieDetailView;
import com.nuon.peter.demoapp.rest.models.ResultsItem;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by beniten on 12/27/16.
 */

public class PopularityAdapter extends RecyclerView.Adapter<PopularityAdapter.ViewHolder> {
  private Context context;
  private List<ResultsItem> moviesItems = new LinkedList<>();
  private MovieDetailView view;

  public PopularityAdapter(Context context, MovieDetailView view){
    this.context = context;
    this.view = view;
  }

  @Override public PopularityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_home_fragment_recycler_item,parent,false);
    return new PopularityAdapter.ViewHolder(view);
  }

  @Override public void onBindViewHolder(PopularityAdapter.ViewHolder holder, final int position) {
    holder.title.setText(moviesItems.get(position).getTitle());
    Glide.with(context).load(Common.IMAGE_URL+moviesItems.get(position).getPosterPath()).centerCrop().crossFade().into(holder.bgMovie);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        view.navigateToDetail(moviesItems.get(position));
      }
    });
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

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this,itemView);
    }
  }
}
