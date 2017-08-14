package com.nuon.peter.demoapp.ui.moviedetail.interactor.impl;

import com.nuon.peter.demoapp.base.response.BaseResponseListener;
import com.nuon.peter.demoapp.ui.moviedetail.api.MovieDetailAPI;
import com.nuon.peter.demoapp.ui.moviedetail.entity.ResponseMovieDetail;
import com.nuon.peter.demoapp.ui.moviedetail.interactor.MovieDetailInteractor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by manithnuon on 2/12/17.
 */

public class MovieDetailInteractorImpl implements MovieDetailInteractor {

  private BaseResponseListener<ResponseMovieDetail> listener;

  public MovieDetailInteractorImpl(BaseResponseListener<ResponseMovieDetail> listener) {
    this.listener = listener;
  }

  @Override public void requestData(int movid) {
    Call<ResponseMovieDetail> call = MovieDetailAPI.getMovieListManager().getMovieDetail(movid);
    call.enqueue(new Callback<ResponseMovieDetail>() {
      @Override public void onResponse(Call<ResponseMovieDetail> call,
          Response<ResponseMovieDetail> response) {
          if(!response.isSuccessful()) listener.onException(response.errorBody().toString());
          ResponseMovieDetail data = response.body();
          if(null == data) return;
          listener.onSuccess(data);

      }

      @Override public void onFailure(Call<ResponseMovieDetail> call, Throwable t) {
          listener.onError(t.getLocalizedMessage());
      }
    });
  }
}
