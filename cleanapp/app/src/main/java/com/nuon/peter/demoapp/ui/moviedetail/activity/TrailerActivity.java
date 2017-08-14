package com.nuon.peter.demoapp.ui.moviedetail.activity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.nuon.peter.demoapp.R;
import com.nuon.peter.demoapp.base.component.torrent.StreamInfo;
import com.nuon.peter.demoapp.base.component.torrent.TorrentService;
import com.nuon.peter.demoapp.base.component.youtube.YouTubeData;
import com.nuon.peter.demoapp.ui.genres.entity.movie.ResultsItem;
import com.nuon.peter.demoapp.ui.moviedetail.fragment.VideoPlayerFragment;
import java.net.URLDecoder;

/**
 * Created by beniten on 2/14/17.
 */

public class TrailerActivity extends AppCompatActivity implements VideoPlayerFragment.Callback {


  private VideoPlayerFragment mVideoPlayerFragment;
  private StreamInfo mStreamInfo;
  private static String media;

  public static void startActivity(Context context, String media) {
    Intent intent = new Intent(context, TrailerActivity.class);
    if (media != null)
      TrailerActivity.media = media;
    context.startActivity(intent);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_activity_video_player);
    mVideoPlayerFragment = (VideoPlayerFragment) getSupportFragmentManager().findFragmentById(R.id.video_fragment);
    mVideoPlayerFragment.enableSubsButton(false);
    mStreamInfo = new StreamInfo(null, null, null, null, null, null);
    QueryYouTubeTask task = new QueryYouTubeTask();
    task.execute(YouTubeData.getYouTubeVideoId("https://www.youtube.com/watch?v=" + TrailerActivity.media));
  }

  @Override public Long getResumePosition() {
    return 0l;
  }

  @Override public StreamInfo getInfo() {
    return mStreamInfo;
  }

  @Override public TorrentService getService() {
    return null;
  }

  private class QueryYouTubeTask extends AsyncTask<String, Void, Uri> {

    private boolean mShowedError = false;

    @Override
    protected Uri doInBackground(String... params) {
      String uriStr = null;
      String quality = "17";   // 3gpp medium quality, which should be fast enough to view over EDGE connection
      String videoId = params[0];

      if (isCancelled())
        return null;

      try {
        @SuppressLint("WifiManagerLeak") WifiManager
            wifiManager = (WifiManager) TrailerActivity.this.getSystemService(Context.WIFI_SERVICE);
        TelephonyManager telephonyManager = (TelephonyManager) TrailerActivity.this.getSystemService(Context.TELEPHONY_SERVICE);

        // if we have a fast connection (wifi or 3g), then we'll get a high quality YouTube video
        if (wifiManager.isWifiEnabled() && wifiManager.getConnectionInfo() != null && wifiManager.getConnectionInfo().getIpAddress() != 0) {
          quality = "22";
        } else if (telephonyManager.getDataState() == TelephonyManager.DATA_CONNECTED &&
            (
                telephonyManager.getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS ||
                    telephonyManager.getNetworkType() == TelephonyManager.NETWORK_TYPE_HSUPA ||
                    telephonyManager.getNetworkType() == TelephonyManager.NETWORK_TYPE_HSPA ||
                    telephonyManager.getNetworkType() == TelephonyManager.NETWORK_TYPE_HSDPA ||
                    telephonyManager.getNetworkType() == TelephonyManager.NETWORK_TYPE_EVDO_0 ||
                    telephonyManager.getNetworkType() == TelephonyManager.NETWORK_TYPE_EVDO_A
            )
            ) {
          quality = "18";
        }

        if (isCancelled())
          return null;

        ////////////////////////////////////
        // calculate the actual URL of the video, encoded with proper YouTube token
        uriStr = YouTubeData.calculateYouTubeUrl(quality, true, videoId);

        if (isCancelled())
          return null;

      } catch (Exception e) {
        Log.e(this.getClass().getSimpleName(), "Error occurred while retrieving information from YouTube.", e);
      }

      if (uriStr != null) {
        return Uri.parse(uriStr);
      } else {
        return null;
      }
    }

    @Override
    protected void onPostExecute(Uri result) {
      super.onPostExecute(result);

      try {
        if (isCancelled())
          return;

        if (result == null) {
          throw new RuntimeException("Invalid NULL Url.");
        }

        mStreamInfo.setVideoLocation(URLDecoder.decode(result.toString(),"UTF-8"));
        mVideoPlayerFragment.onMediaReady();
        Log.d("Data::::",URLDecoder.decode(result.toString(),"UTF-8"));

      } catch (Exception e) {
        Log.e(this.getClass().getSimpleName(), "Error playing video!", e);

      }
    }



    @Override
    protected void onProgressUpdate(Void... pValues) {
      super.onProgressUpdate(pValues);
    }

  }
}
