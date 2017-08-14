package com.nuon.peter.demoapp.rest.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nuon.peter.demoapp.R;
import com.nuon.peter.demoapp.rest.Common;
import com.nuon.peter.demoapp.rest.models.cast.CrewItem;
import com.nuon.peter.demoapp.rest.models.cast.ResponseActors;
import com.nuon.peter.demoapp.rest.models.similar.ResultsItem;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by beniten on 12/27/16.
 */

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ViewHolder> {

  private Context context;
  private List<CrewItem> moviesItems = new LinkedList<>();

  public ActorAdapter(Context context){
    this.context = context;
  }

  @Override public ActorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_activity_detail_actor_items,parent,false);
    return new ActorAdapter.ViewHolder(view);
  }

  @Override public void onBindViewHolder(ActorAdapter.ViewHolder holder, int position) {
    Transformation<Bitmap> bitmapTransformation = new CropCircleTransformation(context);
    Glide.with(context).load("http://image.tmdb.org/t/p/w500" + moviesItems.get(position).getProfilePath())
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.RESULT)
            .crossFade()
            .bitmapTransform(bitmapTransformation)
            .into(holder.bgMovie);
    holder.title.setText(moviesItems.get(position).getName());
  }

  public void appendToList(List<CrewItem> list) {
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

    @BindView(R.id.title_actor) public TextView title;
    @BindView(R.id.iv_image_music) public ImageView bgMovie;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this,itemView);
    }
  }


}
