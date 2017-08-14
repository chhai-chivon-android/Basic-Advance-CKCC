package com.nuon.peter.demoapp.ui.movielist.presenter.impl;

import com.nuon.peter.demoapp.base.response.BaseMultiLoadedListener;
import com.nuon.peter.demoapp.ui.movielist.entity.ResponseMovies;
import com.nuon.peter.demoapp.ui.movielist.interactor.NowPlayingInteractor;
import com.nuon.peter.demoapp.ui.movielist.interactor.impl.NowPlayingInteractorImpl;
import com.nuon.peter.demoapp.ui.movielist.presenter.NowPlayingPresenter;
import com.nuon.peter.demoapp.ui.movielist.view.MovieListView;

/**
 * Created by beniten on 2/10/17.
 */

public class NowPlayingPresenterImpl implements NowPlayingPresenter,BaseMultiLoadedListener<ResponseMovies> {

  private MovieListView view;
  private NowPlayingInteractor interactor;

  public NowPlayingPresenterImpl(MovieListView view) {
    this.view = view;
    interactor = new NowPlayingInteractorImpl(this);
  }

  @Override public void requestData(int page,int which) {
    interactor.requestData(page,which);
  }

  @Override public void onSuccess(ResponseMovies data) {
    view.responseNowPlaying(data);
  }

  @Override public void onError(int which, String msg) {

  }

  @Override public void onException(String msg) {
    view.showException(msg);
  }
}
