package com.nuon.peter.demoapp.ui.home.interactor.impl;

import com.nuon.peter.demoapp.base.response.BaseResponseListener;
import com.nuon.peter.demoapp.ui.home.api.MovieAPI;
import com.nuon.peter.demoapp.ui.movielist.entity.ResponseMovies;
import com.nuon.peter.demoapp.ui.home.interactor.HomeInteractor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by beniten on 2/10/17.
 */

public class HomeInteractorImpl implements HomeInteractor {

  private BaseResponseListener<ResponseMovies> listener;

  public HomeInteractorImpl(BaseResponseListener<ResponseMovies> listener) {
    this.listener = listener;
  }

  @Override public void requestData(int page) {
    Call<ResponseMovies> call = MovieAPI.getMovieManager().getPopularity(1);
    call.enqueue(new Callback<ResponseMovies>() {
      @Override
      public void onResponse(Call<ResponseMovies> call, Response<ResponseMovies> response) {
          if(!response.isSuccessful()) listener.onException(response.errorBody().toString());
          ResponseMovies data = response.body();
          if(null == data) return;
          listener.onSuccess(data);
      }

      @Override public void onFailure(Call<ResponseMovies> call, Throwable t) {
          listener.onError(t.getLocalizedMessage());
      }
    });
  }
}
