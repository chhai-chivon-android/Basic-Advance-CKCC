package com.nuon.peter.demoapp.ui.home.view;

import com.nuon.peter.demoapp.base.response.BaseView;
import com.nuon.peter.demoapp.ui.movielist.entity.ResponseMovies;

/**
 * Created by beniten on 2/10/17.
 */

public interface HomeView extends BaseView{

  void responseData(ResponseMovies movies);

}
