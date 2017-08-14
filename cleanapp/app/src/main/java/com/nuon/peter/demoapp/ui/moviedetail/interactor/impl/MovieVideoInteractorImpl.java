package com.nuon.peter.demoapp.ui.moviedetail.interactor.impl;

import com.nuon.peter.demoapp.base.response.BaseResponseListener;
import com.nuon.peter.demoapp.ui.moviedetail.api.MovieDetailAPI;
import com.nuon.peter.demoapp.ui.moviedetail.entity.videos.ResponseVideos;
import com.nuon.peter.demoapp.ui.moviedetail.interactor.MovieVideoInteractor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by manithnuon on 2/17/17.
 */

public class MovieVideoInteractorImpl implements MovieVideoInteractor {
  private BaseResponseListener<ResponseVideos> listener;

  public MovieVideoInteractorImpl(BaseResponseListener<ResponseVideos> listener) {
    this.listener = listener;
  }

  @Override public void requestData(int id) {
    Call<ResponseVideos> call = MovieDetailAPI.getMovieListManager().getVideosOfMovie(id);
    call.enqueue(new Callback<ResponseVideos>() {
      @Override
      public void onResponse(Call<ResponseVideos> call, Response<ResponseVideos> response) {
        if(!response.isSuccessful()) listener.onException(response.errorBody().toString());
        ResponseVideos data = response.body();
        if(null == data) return;
        listener.onSuccess(data);
      }

      @Override public void onFailure(Call<ResponseVideos> call, Throwable t) {
        listener.onError(t.getLocalizedMessage());
      }
    });
  }
}
