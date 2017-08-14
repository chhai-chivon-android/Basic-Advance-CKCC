package com.nuon.peter.demoapp.ui.movielist.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.nuon.peter.demoapp.R;
import com.nuon.peter.demoapp.utils.Common;
import com.nuon.peter.demoapp.ui.movielist.adapter.NowPlayingAdapter;
import com.nuon.peter.demoapp.ui.movielist.adapter.PopularityAdapter;
import com.nuon.peter.demoapp.ui.movielist.adapter.RatingAdapter;
import com.nuon.peter.demoapp.ui.movielist.adapter.UpCommingAdapter;
import com.nuon.peter.demoapp.ui.movielist.entity.ResponseMovies;
import com.nuon.peter.demoapp.ui.movielist.presenter.NowPlayingPresenter;
import com.nuon.peter.demoapp.ui.movielist.presenter.PopularityPresenter;
import com.nuon.peter.demoapp.ui.movielist.presenter.TopRatePresenter;
import com.nuon.peter.demoapp.ui.movielist.presenter.UpCommingPresenter;
import com.nuon.peter.demoapp.ui.movielist.presenter.impl.NowPlayingPresenterImpl;
import com.nuon.peter.demoapp.ui.movielist.presenter.impl.PopularityPresenterImpl;
import com.nuon.peter.demoapp.ui.movielist.presenter.impl.TopRatePresenterImpl;
import com.nuon.peter.demoapp.ui.movielist.presenter.impl.UpCommingPresenterImpl;
import com.nuon.peter.demoapp.ui.movielist.view.MovieListView;
import com.nuon.peter.demoapp.utils.views.recyclerview.SpacesItemDecoration;

/**
 * Created by beniten on 12/29/16.
 */

public class MovieListFragment extends Fragment implements MovieListView {

  @BindView(R.id.nowplay_recycler) RecyclerView nowplay;
  @BindView(R.id.rating_recycler) RecyclerView rating;
  @BindView(R.id.popularity_recycler) RecyclerView popularity;
  @BindView(R.id.up_comming_recycler) RecyclerView upcoming;

  private View view ;
  private NowPlayingAdapter nowPlayingAdapter;
  private RatingAdapter ratingAdapter;
  private PopularityAdapter popularityAdapter;
  private UpCommingAdapter upCommingAdapter;
  private NowPlayingPresenter nowPlayingPresenter;
  private TopRatePresenter topRatePresenter;
  private PopularityPresenter popularityPresenter;
  private UpCommingPresenter upCommingPresenter;


  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    view = inflater.inflate(R.layout.layout_demo_movie_two,container,false);
    ButterKnife.bind(this,view);

    nowplay.setHasFixedSize(true);
    nowplay.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
    nowplay.addItemDecoration(new SpacesItemDecoration(24));
    nowPlayingAdapter = new NowPlayingAdapter(getContext());
    nowplay.setAdapter(nowPlayingAdapter);
    nowplay.setNestedScrollingEnabled(false);

    rating.setHasFixedSize(true);
    rating.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
    rating.addItemDecoration(new SpacesItemDecoration(24));
    ratingAdapter = new RatingAdapter(getContext());
    rating.setAdapter(ratingAdapter);
    rating.setNestedScrollingEnabled(false);

    popularity.setHasFixedSize(true);
    popularity.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
    popularity.addItemDecoration(new SpacesItemDecoration(24));
    popularityAdapter = new PopularityAdapter(getContext());
    popularity.setAdapter(popularityAdapter);
    popularity.setNestedScrollingEnabled(false);

    upcoming.setHasFixedSize(true);
    upcoming.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
    upcoming.addItemDecoration(new SpacesItemDecoration(24));
    upCommingAdapter = new UpCommingAdapter(getContext());
    upcoming.setAdapter(upCommingAdapter);
    upcoming.setNestedScrollingEnabled(false);

    return view;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    nowPlayingPresenter = new NowPlayingPresenterImpl(this);
    nowPlayingPresenter.requestData(1, Common.STATE_NOW_PLAY);

    topRatePresenter = new TopRatePresenterImpl(this);
    topRatePresenter.requestData(1,Common.STATE_TOP_RATE);

    popularityPresenter = new PopularityPresenterImpl(this);
    popularityPresenter.requestData(1,Common.STATE_POPULARITY);

    upCommingPresenter = new UpCommingPresenterImpl(this);
    upCommingPresenter.requestData(1,Common.STATE_UPCOMING);

  }

  @Override public void responseNowPlaying(ResponseMovies movies) {
    nowPlayingAdapter.appendToList(movies.getResults());
    nowPlayingAdapter.notifyDataSetChanged();
  }

  @Override public void responseTopRate(ResponseMovies movies) {
    ratingAdapter.appendToList(movies.getResults());
    ratingAdapter.notifyDataSetChanged();
  }

  @Override public void responsePopularity(ResponseMovies movies) {
    popularityAdapter.appendToList(movies.getResults());
    popularityAdapter.notifyDataSetChanged();
  }

  @Override public void responseUpComming(ResponseMovies movies) {
    upCommingAdapter.appendToList(movies.getResults());
    upCommingAdapter.notifyDataSetChanged();
    ;
  }

  @Override public void showError(int event, String msg) {

  }

  @Override public void showException(String msg) {

  }
}
