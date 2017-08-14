package com.nuon.peter.demoapp.base.component.providers.media.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.nuon.peter.demoapp.base.component.providers.media.MediaProvider;
import com.nuon.peter.demoapp.base.component.providers.media.SubsProvider;
import java.util.HashMap;
import java.util.Map;

public class Movie extends Media implements Parcelable {
    public String type = "movie";
    public String trailer = "";
    public String runtime = "";
    public String synopsis = "No synopsis available";
    public String certification = "n/a";
    public Map<String, Torrent> torrents = new HashMap<String, Torrent>();

    public Movie(MediaProvider mediaProvider, SubsProvider subsProvider) {
        super(mediaProvider, subsProvider);
        isMovie = true;
    }

    protected Movie(Parcel in) {
        super(in);
        trailer = in.readString();
        runtime = in.readString();
        synopsis = in.readString();
        certification = in.readString();
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
        dest.writeString(trailer);
        dest.writeString(runtime);
        dest.writeString(synopsis);
        dest.writeString(certification);
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
    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

}
