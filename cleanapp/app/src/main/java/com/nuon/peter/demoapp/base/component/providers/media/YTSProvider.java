package com.nuon.peter.demoapp.base.component.providers.media;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.LinkedTreeMap;
import com.nuon.peter.demoapp.R;
import com.nuon.peter.demoapp.app.App;
import com.nuon.peter.demoapp.base.component.providers.media.models.Genre;
import com.nuon.peter.demoapp.base.component.providers.media.models.Media;
import com.nuon.peter.demoapp.base.component.providers.media.models.Movie;
import com.nuon.peter.demoapp.base.network.OKHttpManager;
import com.nuon.peter.demoapp.utils.Common;
import com.nuon.peter.demoapp.utils.LocaleUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

public class YTSProvider extends MediaProvider {

    private static final YTSProvider sMediaProvider = new YTSProvider();
    private static Integer CURRENT_API = 0;
    private static final String[] API_URLS = Common.MOVIE_URLS;

    private static final SubsProvider sSubsProvider = new YSubsProvider();
    private static Filters sFilters = new Filters();

    @Override
    protected OkHttpClient getClient() {
        OkHttpClient client = OKHttpManager.getInstance();
        // Use only HTTP 1.1 for YTS
        List<Protocol> proto = new ArrayList<>();
        proto.add(Protocol.HTTP_1_1);
        client.protocols().addAll(proto);
        return client;
    }

    @Override
    protected Call enqueue(Request request, okhttp3.Callback requestCallback) {
        Context context = App.getInstance();
        PackageInfo pInfo;
        String versionName = "0.0.0";
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionName = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        request = request.newBuilder().removeHeader("User-Agent").addHeader("User-Agent", String.format("Mozilla/5.0 (Linux; U; Android %s; %s; %s Build/%s) AppleWebkit/534.30 (KHTML, like Gecko) PT/%s", Build.VERSION.RELEASE, LocaleUtils
            .getCurrentAsString(), Build.MODEL, Build.DISPLAY, versionName)).build();
        return super.enqueue(request, requestCallback);
    }

    @Override
    public Call getList(final ArrayList<Media> existingList, Filters filters, final Callback callback) {
        sFilters = filters;

        final ArrayList<Media> currentList;
        if (existingList == null) {
            currentList = new ArrayList<>();
        } else {
            currentList = (ArrayList<Media>) existingList.clone();
        }

        ArrayList<NameValuePair> params = new ArrayList<>();
        params.add(new NameValuePair("limit", "30"));

        if (filters == null) {
            filters = new Filters();
        }

        if (filters.keywords != null) {
            params.add(new NameValuePair("query_term", filters.keywords));
        }

        if (filters.genre != null) {
            params.add(new NameValuePair("genre", filters.genre));
        }

        if (filters.order == Filters.Order.ASC) {
            params.add(new NameValuePair("order_by", "asc"));
        } else {
            params.add(new NameValuePair("order_by", "desc"));
        }

        if(filters.langCode != null) {
            params.add(new NameValuePair("lang", filters.langCode));
        }

        String sort;
        switch (filters.sort) {
            default:
            case POPULARITY:
                sort = "seeds";
                break;
            case YEAR:
                sort = "year";
                break;
            case DATE:
                sort = "date_added";
                break;
            case RATING:
                sort = "rating";
                break;
            case ALPHABET:
                sort = "title";
                break;
            case TRENDING:
                sort = "trending_score";
                break;
        }

        params.add(new NameValuePair("sort_by", sort));

        if (filters.page != null) {
            params.add(new NameValuePair("page", Integer.toString(filters.page)));
        }

        Request.Builder requestBuilder = new Request.Builder();
        String query = buildQuery(params);
        requestBuilder.url(API_URLS[CURRENT_API] + "list_movies_pct.json?" + query);
        requestBuilder.tag(MEDIA_CALL);

        return fetchList(currentList, requestBuilder, filters, callback);
    }

    /**
     * Fetch the list of movies from YTS
     *
     * @param currentList    Current shown list to be extended
     * @param requestBuilder Request to be executed
     * @param callback       Network callback
     * @return Call
     */
    private Call fetchList(final ArrayList<Media> currentList, final Request.Builder requestBuilder, final Filters filters, final Callback callback) {
        return enqueue(requestBuilder.build(), new okhttp3.Callback() {
            @Override public void onFailure(Call call, IOException e) {
                String url = requestBuilder.build().url().toString();
                if (CURRENT_API >= API_URLS.length - 1) {
                    callback.onFailure(e);
                } else {
                    if(url.contains(API_URLS[CURRENT_API])) {
                        url = url.replace(API_URLS[CURRENT_API], API_URLS[CURRENT_API + 1]);
                        CURRENT_API++;
                    } else {
                        url = url.replace(API_URLS[CURRENT_API - 1], API_URLS[CURRENT_API]);
                    }
                    requestBuilder.url(url);
                    fetchList(currentList, requestBuilder, filters, callback);
                }
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseStr;
                    try {
                        responseStr = response.body().string();
                    } catch (SocketException e) {
                        onFailure(call, new IOException("Socket failed"));
                        return;
                    }

                    YTSReponse result;
                    try {
                        result = mGson.fromJson(responseStr, YTSReponse.class);
                    } catch (IllegalStateException e) {
                        onFailure(call, new IOException("JSON Failed"));
                        return;
                    } catch (JsonSyntaxException e) {
                        onFailure(call, new IOException("JSON Failed"));
                        return;
                    }

                    if(result == null) {
                        callback.onFailure(new NetworkErrorException("No response"));
                    } else if (result.status != null && result.status.equals("error")) {
                        callback.onFailure(new NetworkErrorException(result.status_message));
                    } else if(result.data != null && ((result.data.get("movies") != null && ((ArrayList<LinkedTreeMap<String, Object>>)result.data.get("movies")).size() <= 0) || ((Double)result.data.get("movie_count")).intValue() <= currentList.size())) {
                        callback.onFailure(new NetworkErrorException("No movies found"));
                    } else {
                        ArrayList<Media> formattedData = result.formatForPopcorn(currentList);
                        callback.onSuccess(filters, formattedData, true);
                        return;
                    }
                }
                onFailure(call, new IOException("Couldn't connect to YTS"));
            }
        });
    }

    @Override
    public Call getDetail(ArrayList<Media> currentList, Integer index, Callback callback) {
        ArrayList<Media> returnList = new ArrayList<>();
        returnList.add(currentList.get(index));
        callback.onSuccess(null, returnList, true);
        return null;
    }

    private class YTSReponse {
        public String status;
        public String status_message;
        public LinkedTreeMap<String, Object> data;

        /**
         * Test if there is an item that already exists
         *
         * @param results List with items
         * @param id      Id of item to check for
         * @return Return the index of the item in the results
         */
        private int isInResults(ArrayList<Media> results, String id) {
            int i = 0;
            for (Media item : results) {
                if (item.videoId.equals(id)) return i;
                i++;
            }
            return -1;
        }

        /**
         * Format data for the application
         *
         * @param existingList List to be extended
         * @return List with items
         */
        public ArrayList<Media> formatForPopcorn(ArrayList<Media> existingList) {
            ArrayList<LinkedTreeMap<String, Object>> movies = new ArrayList<>();
            if (data != null) {
                movies = (ArrayList<LinkedTreeMap<String, Object>>) data.get("movies");
            }

            for (LinkedTreeMap<String, Object> item : movies) {
                Movie movie = new Movie(sMediaProvider, sSubsProvider);

                movie.videoId = (String) item.get("imdb_code");
                movie.imdbId = movie.videoId;

                int existingItem = isInResults(existingList, movie.videoId);
                if (existingItem == -1) {
                    movie.title = (String) item.get("title_english");
                    Double year = (Double) item.get("year");
                    movie.year = Integer.toString(year.intValue());
                    movie.rating = item.get("rating").toString();
                    movie.genre = ((ArrayList<String>) item.get("genres")).get(0);
                    movie.image = (String) item.get("large_cover_image");
                    movie.headerImage = (String) item.get("background_image_original");
                    movie.trailer = "https://youtube.com/watch?v=" + item.get("yt_trailer_code");
                    Double runtime = (Double) item.get("runtime");
                    movie.runtime = Integer.toString(runtime.intValue());
                    movie.synopsis = (String) item.get("description_full");
                    movie.certification = (String) item.get("mpa_rating");
                    movie.fullImage = movie.image;

                    ArrayList<LinkedTreeMap<String, Object>> torrents =
                            (ArrayList<LinkedTreeMap<String, Object>>) item.get("torrents");
                    if (torrents != null) {
                        for (LinkedTreeMap<String, Object> torrentObj : torrents) {
                            String quality = (String) torrentObj.get("quality");
                            if (quality == null) continue;

                            Media.Torrent torrent = new Media.Torrent();

                            torrent.seeds = ((Double) torrentObj.get("seeds")).intValue();
                            torrent.peers = ((Double) torrentObj.get("peers")).intValue();
                            torrent.hash = (String) torrentObj.get("hash");
                            try {
                                torrent.url = "magnet:?xt=urn:btih:" + torrent.hash + "&amp;dn=" + URLEncoder
                                    .encode(item.get("title").toString(), "utf-8") + "&amp;tr=http://exodus.desync.com:6969/announce&amp;tr=udp://tracker.openbittorrent.com:80/announce&amp;tr=udp://open.demonii.com:1337/announce&amp;tr=udp://exodus.desync.com:6969/announce&amp;tr=udp://tracker.yify-torrents.com/announce";
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                                torrent.url = (String) torrentObj.get("url");
                            }

                            movie.torrents.put(quality, torrent);
                        }
                    }

                    existingList.add(movie);
                }
            }
            return existingList;
        }
    }

    @Override
    public int getLoadingMessage() {
        return R.string.loading_movies;
    }

    @Override
    public List<NavInfo> getNavigation() {
        List<NavInfo> tabs = new ArrayList<>();
        tabs.add(new NavInfo(R.id.yts_filter_trending,Filters.Sort.TRENDING, Filters.Order.DESC, App.getInstance().getString(R.string.trending),R.drawable.yts_filter_trending));
        tabs.add(new NavInfo(R.id.yts_filter_popular_now,Filters.Sort.POPULARITY, Filters.Order.DESC, App.getInstance().getString(R.string.popular),R.drawable.yts_filter_popular_now));
        tabs.add(new NavInfo(R.id.yts_filter_top_rated,Filters.Sort.RATING, Filters.Order.DESC, App.getInstance().getString(R.string.top_rated),R.drawable.yts_filter_top_rated));
        tabs.add(new NavInfo(R.id.yts_filter_release_date,Filters.Sort.DATE, Filters.Order.DESC, App.getInstance().getString(R.string.release_date),R.drawable.yts_filter_release_date));
        tabs.add(new NavInfo(R.id.yts_filter_year,Filters.Sort.YEAR, Filters.Order.DESC, App.getInstance().getString(R.string.year),R.drawable.yts_filter_year));
        tabs.add(new NavInfo(R.id.yts_filter_a_to_z,Filters.Sort.ALPHABET, Filters.Order.ASC, App.getInstance().getString(R.string.a_to_z),R.drawable.yts_filter_a_to_z));
        return tabs;
    }

    @Override
    public List<Genre> getGenres() {
        List<Genre> returnList = new ArrayList<>();
        returnList.add(new Genre(null, R.string.genre_all));
        returnList.add(new Genre("action", R.string.genre_action));
        returnList.add(new Genre("adventure", R.string.genre_adventure));
        returnList.add(new Genre("animation", R.string.genre_animation));
        returnList.add(new Genre("biography", R.string.genre_biography));
        returnList.add(new Genre("comedy", R.string.genre_comedy));
        returnList.add(new Genre("crime", R.string.genre_crime));
        returnList.add(new Genre("documentary", R.string.genre_documentary));
        returnList.add(new Genre("drama", R.string.genre_drama));
        returnList.add(new Genre("family", R.string.genre_family));
        returnList.add(new Genre("fantasy", R.string.genre_fantasy));
        returnList.add(new Genre("filmnoir", R.string.genre_film_noir));
        returnList.add(new Genre("history", R.string.genre_history));
        returnList.add(new Genre("horror", R.string.genre_horror));
        returnList.add(new Genre("music", R.string.genre_music));
        returnList.add(new Genre("musical", R.string.genre_musical));
        returnList.add(new Genre("mystery", R.string.genre_mystery));
        returnList.add(new Genre("romance", R.string.genre_romance));
        returnList.add(new Genre("scifi", R.string.genre_sci_fi));
        returnList.add(new Genre("short", R.string.genre_short));
        returnList.add(new Genre("sport", R.string.genre_sport));
        returnList.add(new Genre("thriller", R.string.genre_thriller));
        returnList.add(new Genre("war", R.string.genre_war));
        returnList.add(new Genre("western", R.string.genre_western));
        return returnList;
    }

}
