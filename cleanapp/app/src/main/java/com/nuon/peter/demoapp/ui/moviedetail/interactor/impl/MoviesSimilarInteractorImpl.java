package com.nuon.peter.demoapp.ui.moviedetail.interactor.impl;

import com.nuon.peter.demoapp.base.response.BaseResponseListener;
import com.nuon.peter.demoapp.ui.moviedetail.api.MovieDetailAPI;
import com.nuon.peter.demoapp.ui.moviedetail.entity.similar.ResponseSimilar;
import com.nuon.peter.demoapp.ui.moviedetail.interactor.MoviesSimilarInteractor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by manithnuon on 2/17/17.
 */

public class MoviesSimilarInteractorImpl implements MoviesSimilarInteractor {

  private BaseResponseListener<ResponseSimilar> listener;

  public MoviesSimilarInteractorImpl(BaseResponseListener<ResponseSimilar> listener) {
    this.listener = listener;
  }

  @Override public void requestData(int id) {
    Call<ResponseSimilar> call = MovieDetailAPI.getMovieListManager().getSimilarMovies(id);
    call.enqueue(new Callback<ResponseSimilar>() {
      @Override
      public void onResponse(Call<ResponseSimilar> call, Response<ResponseSimilar> response) {
        if(!response.isSuccessful()) listener.onException(response.errorBody().toString());
        ResponseSimilar data = response.body();
        if(null == data) return;
        listener.onSuccess(data);
      }

      @Override public void onFailure(Call<ResponseSimilar> call, Throwable t) {
        listener.onError(t.getLocalizedMessage());
      }
    });
  }
}
