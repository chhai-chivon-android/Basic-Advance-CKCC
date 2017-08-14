package com.nuon.peter.demoapp.ui.genres.interactor.impl;

import com.nuon.peter.demoapp.base.response.BaseMultiLoadedListener;
import com.nuon.peter.demoapp.base.response.BaseMultiLoadedWithMoreListener;
import com.nuon.peter.demoapp.base.response.BaseResponseListener;
import com.nuon.peter.demoapp.ui.genres.api.GenreAPI;
import com.nuon.peter.demoapp.ui.genres.entity.genre.ResponseGenres;
import com.nuon.peter.demoapp.ui.genres.interactor.GenresInteractor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by manithnuon on 2/11/17.
 */

public class GenresInteractorImpl implements GenresInteractor {

  private BaseMultiLoadedListener<ResponseGenres> listener;

  public GenresInteractorImpl(BaseMultiLoadedListener<ResponseGenres> listener) {
    this.listener = listener;
  }

  @Override public void requestData(final int which) {
    Call<ResponseGenres> call = GenreAPI.getGenreManager().getMovieListGenre();
    call.enqueue(new Callback<ResponseGenres>() {
      @Override
      public void onResponse(Call<ResponseGenres> call, Response<ResponseGenres> response) {
        if(!response.isSuccessful()) listener.onException(response.errorBody().toString());
        ResponseGenres data = response.body();
        if(null == data) return;
        listener.onSuccess(data);
      }
      @Override public void onFailure(Call<ResponseGenres> call, Throwable t) {
        listener.onError(which,t.getLocalizedMessage());
      }
    });
  }
}
