package com.nuon.peter.demoapp.ui.movielist.presenter.impl;

import com.nuon.peter.demoapp.base.response.BaseMultiLoadedListener;
import com.nuon.peter.demoapp.ui.movielist.entity.ResponseMovies;
import com.nuon.peter.demoapp.ui.movielist.interactor.PopularityInteractor;
import com.nuon.peter.demoapp.ui.movielist.interactor.impl.PopularityInteractorImpl;
import com.nuon.peter.demoapp.ui.movielist.presenter.PopularityPresenter;
import com.nuon.peter.demoapp.ui.movielist.view.MovieListView;

/**
 * Created by beniten on 2/10/17.
 */

public class PopularityPresenterImpl implements PopularityPresenter,BaseMultiLoadedListener<ResponseMovies> {

  private MovieListView view;
  private PopularityInteractor interactor;

  public PopularityPresenterImpl(MovieListView view) {
    this.view = view;
    interactor = new PopularityInteractorImpl(this);
  }

  @Override public void onSuccess(ResponseMovies data) {
    view.responsePopularity(data);
  }

  @Override public void onError(int which, String msg) {
    view.showError(which,msg);
  }

  @Override public void onException(String msg) {
    view.showException(msg);
  }

  @Override public void requestData(int page, int which) {
    interactor.requestData(page,which);
  }
}
