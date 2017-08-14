package com.nuon.peter.demoapp.ui.moviedetail.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.nuon.peter.demoapp.R;
import com.nuon.peter.demoapp.databinding.LayoutActivityDetailMovieBinding;
import com.nuon.peter.demoapp.ui.genres.entity.movie.ResultsItem;
import com.nuon.peter.demoapp.ui.moviedetail.adapter.MovieActorBindingAdapter;
import com.nuon.peter.demoapp.ui.moviedetail.adapter.MovieSimilarBindingAdapter;
import com.nuon.peter.demoapp.ui.moviedetail.entity.cast.ResponseActors;
import com.nuon.peter.demoapp.ui.moviedetail.entity.similar.ResponseSimilar;
import com.nuon.peter.demoapp.ui.moviedetail.entity.videos.ResponseVideos;
import com.nuon.peter.demoapp.ui.moviedetail.presenter.MovieActorPresenter;
import com.nuon.peter.demoapp.ui.moviedetail.presenter.MovieVideoPresenter;
import com.nuon.peter.demoapp.ui.moviedetail.presenter.MoviesSimilarPresenter;
import com.nuon.peter.demoapp.ui.moviedetail.presenter.impl.MovieActorPresenterImpl;
import com.nuon.peter.demoapp.ui.moviedetail.presenter.impl.MovieVideoPresenterImpl;
import com.nuon.peter.demoapp.ui.moviedetail.presenter.impl.MoviesSimilarPresenterImpl;
import com.nuon.peter.demoapp.ui.moviedetail.view.MovieActorView;
import com.nuon.peter.demoapp.ui.moviedetail.view.MovieVideoView;
import com.nuon.peter.demoapp.ui.moviedetail.view.MoviesSimilarView;
import com.nuon.peter.demoapp.utils.views.recyclerview.SpacesItemDecoration;

/**
 * Created by manithnuon on 2/17/17.
 */

public class MovieDetailActivity extends AppCompatActivity implements MoviesSimilarView,
    MovieActorView, MovieVideoView{

  private static ResultsItem sMedia;
  private LayoutActivityDetailMovieBinding binding;

  private MoviesSimilarPresenter presenter;
  private MovieSimilarBindingAdapter adapter;
  private MovieActorBindingAdapter actor;
  private MovieActorPresenter actorPresenter;
  private MovieVideoPresenter videoPresenter;
  private String url;

  public static void startActivity(Context context, ResultsItem media) {
    Intent intent = new Intent(context, MovieDetailActivity.class);
    if (media != null)
      sMedia = media;
    context.startActivity(intent);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this,R.layout.layout_activity_detail_movie);
    binding.setMovie(sMedia);
    binding.executePendingBindings();

    presenter = new MoviesSimilarPresenterImpl(this);
    presenter.requestData(sMedia.getId());

    videoPresenter = new MovieVideoPresenterImpl(this);
    videoPresenter.requestData(sMedia.getId());

    actorPresenter = new MovieActorPresenterImpl(this);
    actorPresenter.requestData(sMedia.getId());

    String rate = String.valueOf(sMedia.getVoteAverage());
    binding.ratingBar.setRating(Float.parseFloat(rate));
    if(binding.toolbarApp != null){
      binding.toolbarApp.setTitle("");
      setSupportActionBar(binding.toolbarApp);
      binding.toolbarApp.setNavigationIcon(R.drawable.ic_arrow_back);
      binding.toolbarApp.setNavigationOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
          onBackPressed();
        }
      });
    }
    adapter = new MovieSimilarBindingAdapter(this);
    binding.relatedRecyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    binding.relatedRecyclerview.setHasFixedSize(true);
    binding.relatedRecyclerview.setNestedScrollingEnabled(false);
    binding.relatedRecyclerview.addItemDecoration(new SpacesItemDecoration(32));
    binding.relatedRecyclerview.setAdapter(adapter);

    actor = new MovieActorBindingAdapter(this);
    binding.actorRecyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    binding.actorRecyclerview.setHasFixedSize(true);
    binding.actorRecyclerview.setNestedScrollingEnabled(false);
    binding.actorRecyclerview.addItemDecoration(new SpacesItemDecoration(32));
    binding.actorRecyclerview.setAdapter(actor);


  }

  @Override public void responseSimilarMovie(ResponseSimilar movie) {
      adapter.appendToList(movie.getResults());
  }

  @Override public void showError(String msg) {

  }

  @Override public void showException(String msg) {

  }

  @Override public void responseActor(ResponseActors actors) {
    actor.appendToList(actors.getCast());
  }

  @Override public void responseVideos(ResponseVideos videos) {
      if(videos.getResults().size()>0){
        for(int i = 0;i<videos.getResults().size();i++){
          if(videos.getResults().get(i).getName().contains("Official")){
            url = videos.getResults().get(i).getKey();
            binding.trailerView.setOnClickListener(new View.OnClickListener() {
              @Override public void onClick(View view) {
                TrailerActivity.startActivity(view.getContext(),url);
              }
            });
            break;
          }
        }
      }
  }
}
