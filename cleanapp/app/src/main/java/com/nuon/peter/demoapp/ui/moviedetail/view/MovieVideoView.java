package com.nuon.peter.demoapp.ui.moviedetail.view;

import com.nuon.peter.demoapp.base.response.BaseView;
import com.nuon.peter.demoapp.ui.moviedetail.entity.videos.ResponseVideos;

/**
 * Created by manithnuon on 2/17/17.
 */

public interface MovieVideoView extends BaseView {
  void responseVideos(ResponseVideos videos);
}
