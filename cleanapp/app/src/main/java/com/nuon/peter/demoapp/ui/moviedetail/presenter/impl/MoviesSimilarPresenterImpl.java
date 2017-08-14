package com.nuon.peter.demoapp.ui.moviedetail.presenter.impl;

import com.nuon.peter.demoapp.base.response.BaseResponseListener;
import com.nuon.peter.demoapp.ui.moviedetail.entity.similar.ResponseSimilar;
import com.nuon.peter.demoapp.ui.moviedetail.interactor.MoviesSimilarInteractor;
import com.nuon.peter.demoapp.ui.moviedetail.interactor.impl.MoviesSimilarInteractorImpl;
import com.nuon.peter.demoapp.ui.moviedetail.presenter.MoviesSimilarPresenter;
import com.nuon.peter.demoapp.ui.moviedetail.view.MoviesSimilarView;

/**
 * Created by manithnuon on 2/17/17.
 */

public class MoviesSimilarPresenterImpl implements MoviesSimilarPresenter,BaseResponseListener<ResponseSimilar> {

  private MoviesSimilarView view;
  private MoviesSimilarInteractor interactor;

  public MoviesSimilarPresenterImpl(MoviesSimilarView view) {
    this.view = view;
    interactor = new MoviesSimilarInteractorImpl(this);
  }

  @Override public void onSuccess(ResponseSimilar data) {
    view.responseSimilarMovie(data);
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
