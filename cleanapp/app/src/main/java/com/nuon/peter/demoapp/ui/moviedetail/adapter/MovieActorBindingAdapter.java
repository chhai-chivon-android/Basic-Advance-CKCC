package com.nuon.peter.demoapp.ui.moviedetail.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.nuon.peter.demoapp.R;
import com.nuon.peter.demoapp.databinding.LayoutActivityDetailActorItemBinding;
import com.nuon.peter.demoapp.ui.moviedetail.entity.cast.CastItem;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by beniten on 12/27/16.
 */

public class MovieActorBindingAdapter extends RecyclerView.Adapter<MovieActorBindingAdapter.MovieViewHolder> {

  private Context context;
  private List<CastItem> moviesItems = new LinkedList<>();

  public MovieActorBindingAdapter(Context context){
    this.context = context;
  }

  @Override public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutActivityDetailActorItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.layout_activity_detail_actor_item,parent,false);
    final MovieViewHolder holder = new MovieViewHolder(binding);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
      }
    });
    return holder;
  }

  @Override public void onBindViewHolder(MovieViewHolder holder, int position) {
    CastItem actor = moviesItems.get(position);
    holder.binding.setActor(actor);
  }


  public void appendToList(List<CastItem> list) {
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
    LayoutActivityDetailActorItemBinding binding;
    public MovieViewHolder(LayoutActivityDetailActorItemBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }
  }

}
