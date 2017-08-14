package com.nuon.peter.demoapp.ui.moviedetail.view;

import com.nuon.peter.demoapp.base.response.BaseView;
import com.nuon.peter.demoapp.ui.moviedetail.entity.cast.ResponseActors;

/**
 * Created by manithnuon on 2/17/17.
 */

public interface MovieActorView extends BaseView{
  void responseActor(ResponseActors actors);
}
