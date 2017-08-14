package com.nuon.peter.demoapp.ui.genres.presenter.impl;

import com.nuon.peter.demoapp.base.response.BaseMultiLoadedListener;
import com.nuon.peter.demoapp.base.response.BaseMultiLoadedWithMoreListener;
import com.nuon.peter.demoapp.base.response.BaseResponseListener;
import com.nuon.peter.demoapp.ui.genres.entity.genre.ResponseGenres;
import com.nuon.peter.demoapp.ui.genres.interactor.GenresInteractor;
import com.nuon.peter.demoapp.ui.genres.interactor.impl.GenresInteractorImpl;
import com.nuon.peter.demoapp.ui.genres.presenter.GenresPresenter;
import com.nuon.peter.demoapp.ui.genres.view.GenresView;

/**
 * Created by manithnuon on 2/11/17.
 */

public class GenresPresenterImpl implements GenresPresenter,BaseMultiLoadedListener<ResponseGenres> {

  private GenresView view;
  private GenresInteractor interactor;

  public GenresPresenterImpl(GenresView view) {
    this.view = view;
    interactor = new GenresInteractorImpl(this);
  }

  @Override public void onSuccess(ResponseGenres data) {
    view.responseData(data);
  }

  @Override public void onError(int which, String msg) {
    view.showError(which,msg);
  }

  @Override public void onException(String msg) {
    view.showException(msg);
  }

  @Override public void requestData(int which) {
    interactor.requestData(which);
  }
}
