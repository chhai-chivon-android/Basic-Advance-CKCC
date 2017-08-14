package com.nuon.peter.demoapp.base.response;

/**
 * Created by beniten on 2/10/17.
 */

public interface BaseResponseListener<T> {
  /**
   * when data call back success
   *
   * @param data
   */
  void onSuccess(T data);

  /**
   * when data call back error
   *
   * @param msg
   */
  void onError(String msg);

  /**
   * when data call back occurred exception
   *
   * @param msg
   */
  void onException(String msg);
}
