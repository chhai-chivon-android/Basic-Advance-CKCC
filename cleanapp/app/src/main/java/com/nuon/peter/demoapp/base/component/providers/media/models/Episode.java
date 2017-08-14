package com.nuon.peter.demoapp.base.component.providers.media.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.nuon.peter.demoapp.base.component.providers.media.MediaProvider;
import com.nuon.peter.demoapp.base.component.providers.media.SubsProvider;
import com.nuon.peter.demoapp.base.component.providers.meta.MetaProvider;
import java.util.HashMap;
import java.util.Map;

public class Episode extends Media implements Parcelable {
    public String showName;
    public int aired;
    public int episode;
    public int season;
    public String overview;
    public String tvdbId;
    public boolean dateBased;
    public Map<String, Torrent> torrents = new HashMap<>();

    protected MetaProvider mMetaProvider;

    public Episode(MediaProvider mediaProvider, SubsProvider subsProvider, MetaProvider metaProvider) {
        super(mediaProvider, subsProvider);
        mMetaProvider = metaProvider;
    }

    protected Episode(Parcel in) {
        super(in);
        aired = in.readInt();
        episode = in.readInt();
        season = in.readInt();
        title = in.readString();
        overview = in.readString();
        tvdbId = in.readString();
        showName = in.readString();
        dateBased = in.readInt() == 1;

        String className = in.readString();
        mMetaProvider = null;
        try {
            Class<?> clazz = Class.forName(className);
            mMetaProvider = (MetaProvider) clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            String key = in.readString();
            Torrent torrent = in.readParcelable(Torrent.class.getClassLoader());
            torrents.put(key, torrent);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(aired);
        dest.writeInt(episode);
        dest.writeInt(season);
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeString(tvdbId);
        dest.writeString(showName);
        dest.writeInt(dateBased ? 1 : 0);
        dest.writeString(mMetaProvider != null ? mMetaProvider.getClass().getCanonicalName() : "");
        if (torrents != null) {
            dest.writeInt(torrents.size());
            for (String s : torrents.keySet()) {
                dest.writeString(s);
                dest.writeParcelable(torrents.get(s), flags);
            }
        } else {
            dest.writeInt(0);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Episode> CREATOR = new Parcelable.Creator<Episode>() {
        @Override
        public Episode createFromParcel(Parcel in) {
            return new Episode(in);
        }

        @Override
        public Episode[] newArray(int size) {
            return new Episode[size];
        }
    };

    public MetaProvider getMetaProvider() {
        return mMetaProvider;
    }

}