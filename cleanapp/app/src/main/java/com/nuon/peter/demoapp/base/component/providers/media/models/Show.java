package com.nuon.peter.demoapp.base.component.providers.media.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.nuon.peter.demoapp.base.component.providers.media.MediaProvider;
import com.nuon.peter.demoapp.base.component.providers.media.SubsProvider;
import java.util.LinkedList;


public class Show extends Media implements Parcelable {
    public enum Status {CONTINUING, ENDED, CANCELED, NOT_AIRED_YET, UNKNOWN}

    public String type = "show";
    public String imdbId = "";
    public String airDay = "";
    public String airTime = "";
    public Status status = Status.UNKNOWN;
    public String runtime = "";
    public String network = "";
    public String country = "";
    public String tvdbId = "";
    public String synopsis = "No synopsis available";
    public String certification = "n/a";
    public Integer seasons = 0;
    public LinkedList<Episode> episodes = new LinkedList<>();

    public Show(MediaProvider mediaProvider, SubsProvider subsProvider) {
        super(mediaProvider, subsProvider);
    }

    protected Show(Parcel in) {
        super(in);
        airDay = in.readString();
        airTime = in.readString();
        runtime = in.readString();

        int statusInt = in.readInt();
        if (statusInt == 0) {
            status = Status.CONTINUING;
        } else if (statusInt == 1) {
            status = Status.ENDED;
        } else {
            status = null;
        }

        network = in.readString();
        country = in.readString();
        tvdbId = in.readString();
        imdbId = in.readString();
        synopsis = in.readString();
        certification = in.readString();
        seasons = in.readInt();
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            Episode episode = in.readParcelable(Episode.class.getClassLoader());
            episodes.add(episode);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(airDay);
        dest.writeString(airTime);
        dest.writeString(runtime);
        dest.writeInt(null!=status?status.ordinal(): Status.UNKNOWN.ordinal());
        dest.writeString(network);
        dest.writeString(country);
        dest.writeString(tvdbId);
        dest.writeString(imdbId);
        dest.writeString(synopsis);
        dest.writeString(certification);
        dest.writeInt(seasons == null ? 0 : seasons);
        dest.writeInt(episodes.size());
        for (Episode episode : episodes) {
            dest.writeParcelable(episode, flags);
        }
    }

    @SuppressWarnings("unused")
    public static final Creator<Show> CREATOR = new Creator<Show>() {
        @Override
        public Show createFromParcel(Parcel in) {
            return new Show(in);
        }

        @Override
        public Show[] newArray(int size) {
            return new Show[size];
        }
    };
}
