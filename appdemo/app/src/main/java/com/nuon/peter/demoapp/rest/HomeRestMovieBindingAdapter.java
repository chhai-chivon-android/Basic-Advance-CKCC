package com.nuon.peter.demoapp.rest;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nuon.peter.demoapp.R;
import com.nuon.peter.demoapp.databinding.LayoutMovieItemBinding;
import com.nuon.peter.demoapp.rest.models.ResultsItem;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by beniten on 12/27/16.
 */

public class HomeRestMovieBindingAdapter extends RecyclerView.Adapter<HomeRestMovieBindingAdapter.ViewHolder> {

  private List<ResultsItem> moviesItems = new LinkedList<>();
  private MovieDetailView view;

  public HomeRestMovieBindingAdapter(MovieDetailView view){
    this.view = view;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutMovieItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.layout_movie_item,parent,false);
    return new ViewHolder(binding);

  }

  @Override public void onBindViewHolder(ViewHolder holder, final int position) {
    //holder.title.setText(moviesItems.get(position).getTitle());
    //Glide.with(context).load(Common.IMAGE_URL+moviesItems.get(position).getPosterPath()).centerCrop().crossFade().into(holder.bgMovie);
    ResultsItem movie = moviesItems.get(position);
    holder.binding.setMovie(movie);
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
    LayoutMovieItemBinding binding;

    public ViewHolder(LayoutMovieItemBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }
  }

}
