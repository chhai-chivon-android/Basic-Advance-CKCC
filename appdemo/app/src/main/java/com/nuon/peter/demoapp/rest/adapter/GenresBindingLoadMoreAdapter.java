package com.nuon.peter.demoapp.rest.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nuon.peter.demoapp.R;
import com.nuon.peter.demoapp.databinding.LayoutMovieItemBinding;
import com.nuon.peter.demoapp.rest.MovieDetailView;
import com.nuon.peter.demoapp.rest.models.ResultsItem;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by beniten on 12/27/16.
 */

public class GenresBindingLoadMoreAdapter extends BaseLoadMoreRecyclerAdapter<ResultsItem,GenresBindingLoadMoreAdapter.ViewHolder> {

  private MovieDetailView view;

  public GenresBindingLoadMoreAdapter(MovieDetailView view){
    this.view = view;
  }

  @Override
  public ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
    LayoutMovieItemBinding bind = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.layout_movie_item,parent,false);
    return new GenresBindingLoadMoreAdapter.ViewHolder(bind);
  }

  @Override
  public void onBindItemViewHolder(ViewHolder holder, final int position) {
    holder.binding.setMovie(getItem(position));
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        view.navigateToDetail(getItem(position));
      }
    });
  }

  @Override
  public int getItemViewType(int position) {
    if (position == getBasicItemCount() && hasFooter) {
      return TYPE_FOOTER;
    }
    return TYPE_ITEM;
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    LayoutMovieItemBinding binding;
    public ViewHolder(LayoutMovieItemBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }
  }
}
