package com.nuon.peter.demoapp.ui.moviedetail.view;

import com.nuon.peter.demoapp.base.response.BaseView;
import com.nuon.peter.demoapp.ui.moviedetail.entity.similar.ResponseSimilar;

/**
 * Created by manithnuon on 2/17/17.
 */

public interface MoviesSimilarView extends BaseView {
  void responseSimilarMovie(ResponseSimilar movie);
}
