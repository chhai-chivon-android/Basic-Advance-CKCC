package com.nuon.peter.demoapp.base.component.providers.media.models;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import com.nuon.peter.demoapp.base.component.providers.media.MediaProvider;
import com.nuon.peter.demoapp.base.component.providers.media.SubsProvider;
import java.util.HashMap;
import java.util.Map;

public class Media implements Parcelable {
    public String videoId;
    public String imdbId;
    public String title;
    public String year;
    public String genre;
    public String rating;
    public Boolean isMovie = false;
    public String image;
    public String fullImage;
    public String headerImage;
    public Map<String, String> subtitles;
    public int color = Color.parseColor("#3F51B5");
    protected SubsProvider mSubsProvider = null;
    protected MediaProvider mMediaProvider = null;

    public Media(MediaProvider provider, SubsProvider subsProvider) {
        mMediaProvider = provider;
        mSubsProvider = subsProvider;
    }

    public Media(Parcel in) {
        videoId = in.readString();
        imdbId = in.readString();
        title = in.readString();
        year = in.readString();
        genre = in.readString();
        rating = in.readString();
        isMovie = in.readInt() == 1;
        image = in.readString();
        fullImage = in.readString();
        headerImage = in.readString();
        color = in.readInt();

        String className = in.readString();
        mSubsProvider = null;
        try {
            Class<?> clazz = Class.forName(className);
            mSubsProvider = (SubsProvider) clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        className = in.readString();
        mMediaProvider = null;
        try {
            Class<?> clazz = Class.forName(className);
            mMediaProvider = (MediaProvider) clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int length = in.readInt();
        subtitles = new HashMap<>();
        for (int i = 0; i < length; i++) {
            subtitles.put(in.readString(), in.readString());
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(videoId);
        dest.writeString(imdbId);
        dest.writeString(title);
        dest.writeString(year);
        dest.writeString(genre);
        dest.writeString(rating);
        dest.writeInt(isMovie ? 1 : 2);
        dest.writeString(image);
        dest.writeString(fullImage);
        dest.writeString(headerImage);
        dest.writeInt(color);
        dest.writeString(mSubsProvider != null ? mSubsProvider.getClass().getCanonicalName() : "");
        dest.writeString(mMediaProvider != null ? mMediaProvider.getClass().getCanonicalName() : "");
        if (subtitles != null) {
            dest.writeInt(subtitles.size());
            for (String key : subtitles.keySet()) {
                dest.writeString(key);
                dest.writeString(subtitles.get(key));
            }
        } else {
            dest.writeInt(0);
        }
    }

    @SuppressWarnings("unused")
    public static final Creator<Media> CREATOR = new Creator<Media>() {
        @Override
        public Media createFromParcel(Parcel in) {
            return new Media(in);
        }

        @Override
        public Media[] newArray(int size) {
            return new Media[size];
        }
    };

    public static class Torrent implements Parcelable {
        public String url;
        public Integer seeds;
        public Integer peers;
        public String hash;

        public Torrent() {

        }

        public Torrent(String url, Integer seeds, Integer peers, String hash) {
            this.url = url;
            this.seeds = seeds;
            this.peers = peers;
            this.hash = hash;
        }

        public Torrent(Parcel in) {
            url = in.readString();
            seeds = in.readInt();
            peers = in.readInt();
            hash = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(url);
            dest.writeInt(seeds);
            dest.writeInt(peers);
            dest.writeString(hash);
        }

        @SuppressWarnings("unused")
        public static final Creator<Torrent> CREATOR = new Creator<Torrent>() {
            @Override
            public Torrent createFromParcel(Parcel in) {
                return new Torrent(in);
            }

            @Override
            public Torrent[] newArray(int size) {
                return new Torrent[size];
            }
        };
    }

    public SubsProvider getSubsProvider() {
        return mSubsProvider;
    }

    public MediaProvider getMediaProvider() {
        return mMediaProvider;
    }

}
