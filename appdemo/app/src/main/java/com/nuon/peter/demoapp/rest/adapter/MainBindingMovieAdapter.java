package com.nuon.peter.demoapp.rest.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nuon.peter.demoapp.R;
import com.nuon.peter.demoapp.databinding.LayoutMainFragmentItemBinding;
import com.nuon.peter.demoapp.rest.MovieDetailView;
import com.nuon.peter.demoapp.rest.models.ResultsItem;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by beniten on 12/27/16.
 */

public class MainBindingMovieAdapter extends RecyclerView.Adapter<MainBindingMovieAdapter.ViewHolder> {

  private List<ResultsItem> moviesItems = new LinkedList<>();
  private MovieDetailView view;

  public MainBindingMovieAdapter(MovieDetailView view){
    this.view = view;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutMainFragmentItemBinding bind = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.layout_main_fragment_item,parent,false);
    return new ViewHolder(bind);
  }

  @Override public void onBindViewHolder(ViewHolder holder, final int position) {
    holder.binding.setMovie(moviesItems.get(position));
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
    LayoutMainFragmentItemBinding binding;
    public ViewHolder(LayoutMainFragmentItemBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }
  }

}
