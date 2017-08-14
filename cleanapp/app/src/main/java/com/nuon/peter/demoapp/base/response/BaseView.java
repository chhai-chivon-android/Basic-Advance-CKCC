package com.nuon.peter.demoapp.base.response;

/**
 * Created by beniten on 2/10/17.
 */

public interface BaseView {

  /**
   * show error message
   */
  void showError(String msg);

  /**
   * show exception message
   */
  void showException(String msg);
}
