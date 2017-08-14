package com.nuon.peter.demoapp.ui.moviedetail.presenter.impl;

import com.nuon.peter.demoapp.base.response.BaseResponseListener;
import com.nuon.peter.demoapp.ui.moviedetail.entity.cast.ResponseActors;
import com.nuon.peter.demoapp.ui.moviedetail.interactor.MovieActorInteractor;
import com.nuon.peter.demoapp.ui.moviedetail.interactor.impl.MovieActorInteractorImpl;
import com.nuon.peter.demoapp.ui.moviedetail.presenter.MovieActorPresenter;
import com.nuon.peter.demoapp.ui.moviedetail.view.MovieActorView;

/**
 * Created by manithnuon on 2/17/17.
 */

public class MovieActorPresenterImpl implements MovieActorPresenter,BaseResponseListener<ResponseActors> {
  private MovieActorView view;
  private MovieActorInteractor interactor;

  public MovieActorPresenterImpl(MovieActorView view) {
    this.view = view;
    interactor = new MovieActorInteractorImpl(this);
  }

  @Override public void onSuccess(ResponseActors data) {
    view.responseActor(data);
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
