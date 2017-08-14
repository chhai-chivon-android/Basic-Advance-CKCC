package com.nuon.peter.demoapp.ui.genres.adapter;

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
import com.nuon.peter.demoapp.base.adapters.BaseLoadMoreRecyclerAdapter;
import com.nuon.peter.demoapp.ui.genres.entity.movie.ResultsItem;
import com.nuon.peter.demoapp.ui.genres.view.GenresMoviesView;
import com.nuon.peter.demoapp.ui.home.entity.FeedModel;
import com.nuon.peter.demoapp.ui.home.entity.movies.MoviesItem;
import com.nuon.peter.demoapp.ui.movielist.api.MovieListAPI;
import com.nuon.peter.demoapp.utils.views.slantextview.SlantedTextView;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by beniten on 12/27/16.
 */

public class GenresMoviesAdapter extends BaseLoadMoreRecyclerAdapter<ResultsItem,GenresMoviesAdapter.ViewHolder>{

  private Context context;
  private List<ResultsItem> moviesItems  = new LinkedList<>();
  private GenresMoviesView view;

  public GenresMoviesAdapter(Context context, GenresMoviesView view){
    this.context = context;
    this.view = view;
  }

  @Override
  public ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
    return null;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_fragment_genres_gride,parent,false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindItemViewHolder(ViewHolder holder, final int position) {
    holder.userName.setText(moviesItems.get(position).getReleaseDate());
    holder.userDescription.setText(moviesItems.get(position).getTitle());
    holder.vote.setText(moviesItems.get(position).getVoteAverage()+"");
    Glide.with(context).load(MovieListAPI.getImageURL("w160")+moviesItems.get(position)
            .getPosterPath())
            .centerCrop()
            .crossFade()
            .diskCacheStrategy(DiskCacheStrategy.RESULT)
            .into(holder.userMusicBackground);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View views) {
        view.navigateDetail(moviesItems.get(position));
      }
    });
  }


  public void appendToList(List<ResultsItem> list) {
    if (list == null) {
      return;
    }
    moviesItems.addAll(list);
  }

  public void appendToListNotify(List<ResultsItem> list) {
    if (list == null) {
      return;
    }
    moviesItems.addAll(list);
    notifyItemInserted(moviesItems.size()-1);
  }

  public void clear() {
    moviesItems.clear();
  }

  @Override
  public int getItemViewType(int position) {
    if (position == getBasicItemCount() && hasFooter) {
      return TYPE_FOOTER;
    }
    return TYPE_ITEM;
  }


  @Override public int getItemCount() { return moviesItems.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_user) public TextView userName;
    @BindView(R.id.tv_description) public TextView userDescription;
    @BindView(R.id.iv_image_music) public ImageView userMusicBackground;
    @BindView(R.id.slanted) public SlantedTextView vote;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this,itemView);
    }
  }

}
