package com.nuon.peter.demoapp.ui.movielist.view;

import com.nuon.peter.demoapp.base.response.BaseMultiView;
import com.nuon.peter.demoapp.ui.movielist.entity.ResponseMovies;

/**
 * Created by beniten on 2/10/17.
 */

public interface MovieListView extends BaseMultiView{
  void responseNowPlaying(ResponseMovies movies);
  void responseTopRate(ResponseMovies movies);
  void responsePopularity(ResponseMovies movies);
  void responseUpComming(ResponseMovies movies);
}
