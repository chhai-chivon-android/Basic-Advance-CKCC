package com.nuon.peter.demoapp.ui.genres.presenter.impl;

import com.nuon.peter.demoapp.base.response.BaseMultiLoadedWithMoreListener;
import com.nuon.peter.demoapp.ui.genres.entity.movie.ResponseMovies;
import com.nuon.peter.demoapp.ui.genres.interactor.GenresMoviesInteractor;
import com.nuon.peter.demoapp.ui.genres.interactor.impl.GenresMoviesInteractorImpl;
import com.nuon.peter.demoapp.ui.genres.presenter.GenresMoviesPresenter;
import com.nuon.peter.demoapp.ui.genres.view.GenresMoviesView;
import com.nuon.peter.demoapp.utils.Common;

/**
 * Created by manithnuon on 2/11/17.
 */

public class GenresMoviesPresenterImpl implements GenresMoviesPresenter,BaseMultiLoadedWithMoreListener<ResponseMovies> {

  private GenresMoviesView view;
  private GenresMoviesInteractor interactor;

  public GenresMoviesPresenterImpl(GenresMoviesView view) {
    this.view = view;
    interactor = new GenresMoviesInteractorImpl(this);
  }

  @Override public void onSuccess(int event, ResponseMovies data) {
    if(Common.STATE_GENRES_MOVIES == event){
      view.responseGenres(data);
    } else if(Common.STATE_GENRES_MOVIES_MORE == event) {
      view.responseGenresMore(data);
    }
  }

  @Override public void onError(int which, String msg) {
    view.showError(which,msg);

  }

  @Override public void onException(String msg) {
    view.showException(msg);
  }

  @Override public void requestData(int which, int id, int page) {
    interactor.requestData(which,id,page);
  }
}
