package com.nuon.peter.demoapp.base.component.torrent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Parcelable;
import com.nuon.peter.demoapp.R;
import java.util.ArrayList;
import java.util.List;

public class Magnet {

    private Context mContext;
    private boolean mCanOpen = false;
    private Intent mOpenIntent;

    public Magnet(Context context, String magnetUrl) {
        mContext = context;
        setUrl(magnetUrl);
    }

    public Magnet(Context context, StreamInfo streamInfo) {
        this(context, streamInfo.getTorrentUrl());
    }

    public void setUrl(String magnetUrl) {
        if(magnetUrl == null) {
            mCanOpen = false;
            return;
        }

        Uri uri = Uri.parse(magnetUrl);

        List<Intent> filteredShareIntents = new ArrayList<>();
        Intent torrentIntent = new Intent(Intent.ACTION_VIEW, uri);
        List<ResolveInfo> resolveInfoList = mContext.getPackageManager().queryIntentActivities(torrentIntent, PackageManager.MATCH_DEFAULT_ONLY);

        for (ResolveInfo info : resolveInfoList) {
            if (!info.activityInfo.packageName.contains("pct.droid")) {     // Black listing the app its self
                Intent targetedShare = new Intent(Intent.ACTION_VIEW, uri);
                targetedShare.setPackage(info.activityInfo.packageName);
                filteredShareIntents.add(targetedShare);
            }
        }

        if (filteredShareIntents.size() > 0){
            Intent filteredIntent = Intent.createChooser(filteredShareIntents.remove(0), mContext.getString(
                R.string.open_with));
            filteredIntent.putExtra(
                Intent.EXTRA_INITIAL_INTENTS, filteredShareIntents.toArray(new Parcelable[filteredShareIntents.size()]));
            mOpenIntent = filteredIntent;
            mCanOpen = true;
        } else {
            mCanOpen = false;
        }
    }

    public void open(Activity activity) {
        if(mOpenIntent != null) {
            activity.startActivity(mOpenIntent);
        }
    }

    public boolean canOpen() {
        return mCanOpen;
    }

}
