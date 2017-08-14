package com.nuon.peter.demoapp.base.component.providers;

import android.os.AsyncTask;
import com.google.gson.Gson;
import com.nuon.peter.demoapp.base.network.OKHttpManager;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.ExecutionException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;


/**
 * BaseProvider.java
 * <p/>
 * Base class for providers, has code to enqueue network requests to the OkHttpClient
 */
public abstract class BaseProvider {

    public Gson mGson = new Gson();
    protected Call mCurrentCall;

    protected OkHttpClient getClient() {
        return OKHttpManager.getInstance();
    }

    /**
     * Enqueue request without callback
     *
     * @param request Request
     * @return Call
     */
    protected Call enqueue(Request request) {
        return enqueue(request, null);
    }

    /**
     * Enqueue request with callback
     *
     * @param request         Request
     * @param requestCallback Callback
     * @return Call
     */
    protected Call enqueue(Request request, Callback requestCallback) {
        mCurrentCall = getClient().newCall(request);
        if (requestCallback != null) mCurrentCall.enqueue(requestCallback);
        return mCurrentCall;
    }

    public void cancel() {
        // Cancel in asynctask to prevent networkOnMainThreadException but make it blocking to prevent network calls to be made and then immediately cancelled.
        try {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    getClient().dispatcher().cancelAll();
                    /*getClient().cancel(MediaProvider.MEDIA_CALL);
                    getClient().cancel(MetaProvider.META_CALL);
                    getClient().cancel(SubsProvider.SUBS_CALL);*/
                    return null;
                }
            }.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * Build URL encoded query
     *
     * @param valuePairs List with key-value items
     * @return Query string
     */
    protected String buildQuery(List<NameValuePair> valuePairs) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            for (int i = 0; i < valuePairs.size(); i++) {
                NameValuePair pair = valuePairs.get(i);
                stringBuilder.append(URLEncoder.encode(pair.getName(), "utf-8"));
                stringBuilder.append("=");
                stringBuilder.append(URLEncoder.encode(pair.getValue(), "utf-8"));
                if (i + 1 != valuePairs.size()) stringBuilder.append("&");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        return stringBuilder.toString();
    }

    public class NameValuePair {
        private String mName;
        private String mValue;

        public NameValuePair(String name, String value) {
            mName = name;
            mValue = value;
        }

        public String getName() {
            return mName;
        }

        public String getValue() {
            return mValue;
        }
    }

}
