package com.nuon.peter.demoapp.ui.moviedetail.interactor.impl;

import com.nuon.peter.demoapp.base.response.BaseResponseListener;
import com.nuon.peter.demoapp.ui.moviedetail.api.MovieDetailAPI;
import com.nuon.peter.demoapp.ui.moviedetail.entity.cast.ResponseActors;
import com.nuon.peter.demoapp.ui.moviedetail.interactor.MovieActorInteractor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by manithnuon on 2/17/17.
 */

public class MovieActorInteractorImpl implements MovieActorInteractor {
  private BaseResponseListener<ResponseActors> listener;

  public MovieActorInteractorImpl(BaseResponseListener<ResponseActors> listener) {
    this.listener = listener;
  }

  @Override public void requestData(int id) {
    Call<ResponseActors> call = MovieDetailAPI.getMovieListManager().getCreditsOfMovie(id);
    call.enqueue(new Callback<ResponseActors>() {
      @Override
      public void onResponse(Call<ResponseActors> call, Response<ResponseActors> response) {
        if(!response.isSuccessful()) listener.onException(response.errorBody().toString());
        ResponseActors data = response.body();
        if(null == data) return;
        listener.onSuccess(data);
      }

      @Override public void onFailure(Call<ResponseActors> call, Throwable t) {
        listener.onError(t.getLocalizedMessage());
      }
    });
  }
}
