package com.nuon.peter.demoapp.ui.moviedetail.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
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
import com.nuon.peter.demoapp.databinding.LayoutActivityDetailSimilarItemBinding;
import com.nuon.peter.demoapp.ui.moviedetail.entity.similar.ResultsItem;
import com.nuon.peter.demoapp.ui.movielist.api.MovieListAPI;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by beniten on 12/27/16.
 */

public class MovieSimilarBindingAdapter extends RecyclerView.Adapter<MovieSimilarBindingAdapter.MovieViewHolder> {

  private Context context;
  private List<ResultsItem> moviesItems = new LinkedList<>();

  public MovieSimilarBindingAdapter(Context context){
    this.context = context;
  }

  @Override public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutActivityDetailSimilarItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.layout_activity_detail_similar_item,parent,false);
    final MovieViewHolder holder = new MovieViewHolder(binding);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
      }
    });
    return holder;
  }

  @Override public void onBindViewHolder(MovieViewHolder holder, int position) {
    ResultsItem movie = moviesItems.get(position);
    holder.binding.setMovie(movie);
    Glide.with(context).load(MovieListAPI.getImageURL("w160")+moviesItems.get(position).getPosterPath()).centerCrop().crossFade().diskCacheStrategy(
        DiskCacheStrategy.RESULT).into(holder.binding.ivImageMusic);
  }


  public void appendToList(List<ResultsItem> list) {
    if (list == null) {
      return;
    }
    moviesItems.addAll(list);
    notifyDataSetChanged();
  }

  public void clear() {
    moviesItems.clear();
  }

  @Override public int getItemCount() { return moviesItems.size();
  }

  public static class MovieViewHolder extends RecyclerView.ViewHolder {
    LayoutActivityDetailSimilarItemBinding binding;
    public MovieViewHolder(LayoutActivityDetailSimilarItemBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }
  }

}
