package com.nuon.peter.demoapp.base.response;


public interface BaseMultiLoadedListener<T> {

    /**
     * when data call back success
     * @param data
     */
    void onSuccess(T data);

    /**
     * when data call back error
     *
     * @param msg
     */
    void onError(int which, String msg);

    /**
     * when data call back occurred exception
     *
     * @param msg
     */
    void onException(String msg);
}
