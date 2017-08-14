package com.nuon.peter.demoapp.ui.moviedetail.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.nuon.peter.demoapp.R;
import com.nuon.peter.demoapp.ui.moviedetail.entity.ResponseMovieDetail;
import com.nuon.peter.demoapp.ui.moviedetail.entity.similar.ResponseSimilar;
import com.nuon.peter.demoapp.ui.moviedetail.presenter.MovieDetailPresenter;
import com.nuon.peter.demoapp.ui.moviedetail.presenter.MoviesSimilarPresenter;
import com.nuon.peter.demoapp.ui.moviedetail.presenter.impl.MovieDetailPresenterImpl;
import com.nuon.peter.demoapp.ui.moviedetail.view.MovieDetailView;
import com.nuon.peter.demoapp.ui.moviedetail.view.MoviesSimilarView;

/**
 * Created by manithnuon on 2/12/17.
 */

public class MovieDetailFragment extends Fragment implements MovieDetailView,MoviesSimilarView {

  private View view;
  private MovieDetailPresenter presenter;
  private MoviesSimilarPresenter moviesSimilarPresenter;
  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.layout_demo_movie_two,container,false);
    ButterKnife.bind(this,view);
    return view;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    presenter = new MovieDetailPresenterImpl(this);
    presenter.requestData(28);
  }

  @Override public void responseData(ResponseMovieDetail movies) {

  }

  @Override public void showError(String msg) {

  }

  @Override public void showException(String msg) {

  }

  @Override public void responseSimilarMovie(ResponseSimilar movie) {

  }
}
