package com.nuon.peter.demoapp.ui.genres.view;

import com.nuon.peter.demoapp.base.response.BaseMultiView;
import com.nuon.peter.demoapp.ui.genres.entity.genre.ResponseGenres;

/**
 * Created by manithnuon on 2/11/17.
 */

public interface GenresView extends BaseMultiView{
  void responseData(ResponseGenres genres);
}
