package com.nuon.peter.demoapp.ui.moviedetail.presenter.impl;

import com.nuon.peter.demoapp.base.response.BaseResponseListener;
import com.nuon.peter.demoapp.ui.moviedetail.entity.videos.ResponseVideos;
import com.nuon.peter.demoapp.ui.moviedetail.interactor.MovieVideoInteractor;
import com.nuon.peter.demoapp.ui.moviedetail.interactor.impl.MovieVideoInteractorImpl;
import com.nuon.peter.demoapp.ui.moviedetail.presenter.MovieVideoPresenter;
import com.nuon.peter.demoapp.ui.moviedetail.view.MovieVideoView;

/**
 * Created by manithnuon on 2/17/17.
 */

public class MovieVideoPresenterImpl implements MovieVideoPresenter,BaseResponseListener<ResponseVideos> {

  private MovieVideoView view;
  private MovieVideoInteractor interactor;

  public MovieVideoPresenterImpl(MovieVideoView view) {
    this.view = view;
    interactor = new MovieVideoInteractorImpl(this);
  }

  @Override public void onSuccess(ResponseVideos data) {
    view.responseVideos(data);
  }

  @Override public void onError(String msg) {
    view.showError(msg);
  }

  @Override public void onException(String msg) {
    view.showException(msg);
  }

  @Override public void requestData(int id) {
    interactor.requestData(id);
  }
}
