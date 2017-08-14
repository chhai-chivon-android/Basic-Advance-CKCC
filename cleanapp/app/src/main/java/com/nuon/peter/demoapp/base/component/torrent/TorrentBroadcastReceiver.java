package com.nuon.peter.demoapp.base.component.torrent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TorrentBroadcastReceiver extends BroadcastReceiver {

    public static final String STOP = "pct.droid.base.torrent.TorrentBroadcastReceiver.STOP";
    public static final Integer REQUEST_CODE = 21412;

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(STOP)) {
            TorrentService.stop();
        }
    }

}
