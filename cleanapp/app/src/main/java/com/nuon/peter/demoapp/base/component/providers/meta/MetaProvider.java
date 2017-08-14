package com.nuon.peter.demoapp.base.component.providers.meta;

import com.nuon.peter.demoapp.base.component.providers.BaseProvider;
import org.joda.time.DateTime;

public abstract class MetaProvider extends BaseProvider {
    public static final String META_CALL = "meta_http_call";

    public static class MetaData {
        public String title;
        public Integer year;
        public String imdb_id;
        public DateTime released;
        public String trailer;
        public Integer runtime;
        public Double rating;
        public String tagline;
        public String overview;
        public String certification;
        public Images images;
        public String[] genres;

        public static class Images {
            public String poster;
            public String backdrop;

            public Images(String poster, String backdrop) {
                this.poster = poster;
                this.backdrop = backdrop;
            }

            public Images(String poster) {
                this.poster = this.backdrop = poster;
            }
        }
    }

    public interface Callback {
        public void onResult(MetaData metaData, Exception e);
    }

    public void getMovieMeta(String imdbId, Callback callback) {
    }

    public void getEpisodeMeta(String imdbId, int season, int episode, Callback callback) {
    }

    public void getShowMeta(String imdbId, Callback callback) {
    }

}
