package com.nuon.peter.demoapp.base.response;

/**
 * Created by beniten on 2/10/17.
 */

public interface BaseMultiView {

  /**
   * show error message
   */
  void showError(int event, String msg);

  /**
   * show exception message
   */
  void showException(String msg);
}
