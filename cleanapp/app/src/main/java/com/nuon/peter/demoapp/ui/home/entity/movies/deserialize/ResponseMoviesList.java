package com.nuon.peter.demoapp.ui.home.entity.movies.deserialize;

import com.nuon.peter.demoapp.ui.home.entity.movies.MoviesItem;
import java.util.List;

/**
 * Created by manithnuon on 2/5/17.
 */

public class ResponseMoviesList {
  private List<MoviesItem> movies;
  public void setMovies(List<MoviesItem> movies){
    this.movies = movies;
  }

  public List<MoviesItem> getMovies(){
    return movies;
  }
}
