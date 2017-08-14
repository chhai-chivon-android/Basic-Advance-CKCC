package com.nuon.peter.demoapp.ui.moviedetail.presenter.impl;

import com.nuon.peter.demoapp.base.response.BaseResponseListener;
import com.nuon.peter.demoapp.ui.moviedetail.entity.ResponseMovieDetail;
import com.nuon.peter.demoapp.ui.moviedetail.interactor.MovieDetailInteractor;
import com.nuon.peter.demoapp.ui.moviedetail.interactor.impl.MovieDetailInteractorImpl;
import com.nuon.peter.demoapp.ui.moviedetail.presenter.MovieDetailPresenter;
import com.nuon.peter.demoapp.ui.moviedetail.view.MovieDetailView;

/**
 * Created by manithnuon on 2/12/17.
 */

public class MovieDetailPresenterImpl implements MovieDetailPresenter,BaseResponseListener<ResponseMovieDetail> {

  private MovieDetailView view;
  private MovieDetailInteractor interactor;

  public MovieDetailPresenterImpl(MovieDetailView view) {
    this.view = view;
    interactor = new MovieDetailInteractorImpl(this);
  }

  @Override public void onSuccess(ResponseMovieDetail data) {
    view.responseData(data);

  }

  @Override public void onError(String msg) {
    view.showError(msg);
  }

  @Override public void onException(String msg) {
    view.showException(msg);
  }

  @Override public void requestData(int movid) {
    interactor.requestData(movid);
  }
}
