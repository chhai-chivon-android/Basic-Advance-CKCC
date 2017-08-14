package com.nuon.peter.demoapp.ui.genres.interactor.impl;

import android.util.Log;
import com.nuon.peter.demoapp.base.response.BaseMultiLoadedWithMoreListener;
import com.nuon.peter.demoapp.ui.genres.api.GenreAPI;
import com.nuon.peter.demoapp.ui.genres.entity.movie.ResponseMovies;
import com.nuon.peter.demoapp.ui.genres.interactor.GenresMoviesInteractor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by manithnuon on 2/11/17.
 */

public class GenresMoviesInteractorImpl implements GenresMoviesInteractor {

  private BaseMultiLoadedWithMoreListener<ResponseMovies> listener;

  public GenresMoviesInteractorImpl(BaseMultiLoadedWithMoreListener<ResponseMovies> listener) {
    this.listener = listener;
  }
  @Override public void requestData(final int which,int id, int page) {
    Call<ResponseMovies> call = GenreAPI.getGenreManager().getGenreMoviesPage(id,page);
    call.enqueue(new Callback<ResponseMovies>() {
      @Override
      public void onResponse(Call<ResponseMovies> call, Response<ResponseMovies> response) {
        if(!response.isSuccessful()) listener.onException(response.errorBody().toString());
        ResponseMovies data = response.body();
        if (null == data) return;
        listener.onSuccess(which,data);
      }

      @Override public void onFailure(Call<ResponseMovies> call, Throwable t) {
        listener.onError(which,t.getLocalizedMessage());
        Log.d("MOVIES:::",t.getLocalizedMessage().toString());
      }
    });
  }
}
