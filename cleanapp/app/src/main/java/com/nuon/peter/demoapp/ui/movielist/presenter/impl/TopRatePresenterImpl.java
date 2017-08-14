package com.nuon.peter.demoapp.ui.movielist.presenter.impl;

import com.nuon.peter.demoapp.base.response.BaseMultiLoadedListener;
import com.nuon.peter.demoapp.ui.movielist.entity.ResponseMovies;
import com.nuon.peter.demoapp.ui.movielist.interactor.TopRateInteractor;
import com.nuon.peter.demoapp.ui.movielist.interactor.impl.TopRateInteractorImpl;
import com.nuon.peter.demoapp.ui.movielist.presenter.TopRatePresenter;
import com.nuon.peter.demoapp.ui.movielist.view.MovieListView;

/**
 * Created by beniten on 2/10/17.
 */

public class TopRatePresenterImpl implements TopRatePresenter,BaseMultiLoadedListener<ResponseMovies> {


  private MovieListView view;
  private TopRateInteractor interactor;

  public TopRatePresenterImpl(MovieListView view) {
    this.view = view;
    interactor = new TopRateInteractorImpl(this);
  }

  @Override public void onSuccess(ResponseMovies data) {
      view.responseTopRate(data);
  }

  @Override public void onError(int which ,String msg) {
      view.showError(which,msg);
  }

  @Override public void onException(String msg) {
      view.showException(msg);
  }

  @Override public void requestData(int page, int which) {
      interactor.requestData(page,which);
  }
}
