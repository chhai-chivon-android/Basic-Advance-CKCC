package com.nuon.peter.demoapp.ui.movielist.presenter.impl;

import com.nuon.peter.demoapp.base.response.BaseMultiLoadedListener;
import com.nuon.peter.demoapp.ui.movielist.entity.ResponseMovies;
import com.nuon.peter.demoapp.ui.movielist.interactor.UpCommingInteractor;
import com.nuon.peter.demoapp.ui.movielist.interactor.impl.UpCommingInteractorImpl;
import com.nuon.peter.demoapp.ui.movielist.presenter.UpCommingPresenter;
import com.nuon.peter.demoapp.ui.movielist.view.MovieListView;

/**
 * Created by beniten on 2/10/17.
 */

public class UpCommingPresenterImpl implements UpCommingPresenter,BaseMultiLoadedListener<ResponseMovies> {

  private MovieListView view;
  private UpCommingInteractor interactor;

  public UpCommingPresenterImpl(MovieListView view) {
    this.view = view;
    interactor = new UpCommingInteractorImpl(this);
  }

  @Override public void onSuccess(ResponseMovies data) {
    view.responseUpComming(data);
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
