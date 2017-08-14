package com.nuon.peter.demoapp.ui.moviedetail.view;

import com.nuon.peter.demoapp.base.response.BaseView;
import com.nuon.peter.demoapp.ui.moviedetail.entity.ResponseMovieDetail;

/**
 * Created by manithnuon on 2/12/17.
 */

public interface MovieDetailView extends BaseView {
  void responseData(ResponseMovieDetail movies);
}
