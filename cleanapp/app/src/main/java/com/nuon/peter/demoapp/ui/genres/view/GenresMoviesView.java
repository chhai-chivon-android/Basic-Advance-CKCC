package com.nuon.peter.demoapp.ui.genres.view;

import com.nuon.peter.demoapp.base.response.BaseMultiView;
import com.nuon.peter.demoapp.ui.genres.entity.movie.ResponseMovies;
import com.nuon.peter.demoapp.ui.genres.entity.movie.ResultsItem;

/**
 * Created by manithnuon on 2/11/17.
 */

public interface GenresMoviesView extends BaseMultiView{
  void responseGenres(ResponseMovies movies);
  void responseGenresMore(ResponseMovies movies);
  void navigateDetail(ResultsItem movie);
}
