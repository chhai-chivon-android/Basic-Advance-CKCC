package com.nuon.peter.demoapp.ui.home.presenter.impl;

import com.nuon.peter.demoapp.base.response.BaseResponseListener;
import com.nuon.peter.demoapp.ui.movielist.entity.ResponseMovies;
import com.nuon.peter.demoapp.ui.home.interactor.HomeInteractor;
import com.nuon.peter.demoapp.ui.home.interactor.impl.HomeInteractorImpl;
import com.nuon.peter.demoapp.ui.home.presenter.HomePresenter;
import com.nuon.peter.demoapp.ui.home.view.HomeView;

/**
 * Created by beniten on 2/10/17.
 */

public class HomePresenterImpl implements HomePresenter,BaseResponseListener<ResponseMovies> {

  private HomeView view;
  private HomeInteractor interactor;

  public HomePresenterImpl(HomeView view) {
    this.view = view;
    interactor = new HomeInteractorImpl(this);
  }

  @Override public void onSuccess(ResponseMovies data) {
    view.responseData(data);
  }

  @Override public void onError(String msg) {
    view.showError(msg);
  }

  @Override public void onException(String msg) {
    view.showException(msg);
  }

  @Override public void requestData(int page) {
    interactor.requestData(page);
  }
}
