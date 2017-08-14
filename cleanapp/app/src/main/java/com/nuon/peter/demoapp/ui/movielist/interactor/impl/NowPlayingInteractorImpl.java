package com.nuon.peter.demoapp.ui.movielist.interactor.impl;

import com.nuon.peter.demoapp.base.response.BaseMultiLoadedListener;
import com.nuon.peter.demoapp.ui.movielist.api.MovieListAPI;
import com.nuon.peter.demoapp.ui.movielist.entity.ResponseMovies;
import com.nuon.peter.demoapp.ui.movielist.interactor.NowPlayingInteractor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by beniten on 2/10/17.
 */

public class NowPlayingInteractorImpl implements NowPlayingInteractor {

  private BaseMultiLoadedListener<ResponseMovies> listener;

  public NowPlayingInteractorImpl(BaseMultiLoadedListener<ResponseMovies> listener) {
    this.listener = listener;
  }

  @Override public void requestData(int page, final int whichrequest) {
    Call<ResponseMovies> call = MovieListAPI.getMovieListManager().getNowPlaying(page);
    call.enqueue(new Callback<ResponseMovies>() {
      @Override
      public void onResponse(Call<ResponseMovies> call, Response<ResponseMovies> response) {
        if(!response.isSuccessful()) listener.onException(response.errorBody().toString());
        ResponseMovies data = response.body();
        if(null == data) return;
        listener.onSuccess(data);
      }

      @Override public void onFailure(Call<ResponseMovies> call, Throwable t) {
        listener.onError(whichrequest,t.getLocalizedMessage());
      }
    });
  }
}
