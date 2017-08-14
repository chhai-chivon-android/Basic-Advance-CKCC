package com.nuon.peter.demoapp.base.component.providers.subs;

import com.nuon.peter.demoapp.base.component.providers.media.models.Episode;
import com.nuon.peter.demoapp.base.component.providers.media.models.Movie;
import com.nuon.peter.demoapp.base.component.providers.media.SubsProvider;
import de.timroes.axmlrpc.XMLRPCCallback;
import de.timroes.axmlrpc.XMLRPCClient;
import de.timroes.axmlrpc.XMLRPCException;
import de.timroes.axmlrpc.XMLRPCServerException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class OpenSubsProvider extends SubsProvider {

    protected String mApiUrl = "http://api.opensubtitles.org/xml-rpc";
    protected String mUserAgent = "Popcorn Time v1";//"Popcorn Time Android v1";

    @Override
    public void getList(Movie movie, Callback callback) {
        // Movie subtitles not supported
        callback.onFailure(new NoSuchMethodException("Movie subtitles not supported"));
    }

    @Override
    public void getList(final Episode episode, final Callback callback) {
        login(new XMLRPCCallback() {
            @Override
            public void onResponse(long id, Object result) {
                Map<String, Object> response = (Map<String, Object>) result;
                String token = (String) response.get("token");

                final String episodeStr = Integer.toString(episode.episode);
                final String seasonStr = Integer.toString(episode.season);

                if (!token.isEmpty()) {
                    search(episode, token, new XMLRPCCallback() {
                        @Override
                        public void onResponse(long id, Object result) {
                            Map<String, Map<String, String>> returnMap = new HashMap<>();
                            Map<String, Integer[]> scoreMap = new HashMap<>();
                            Map<String, String> episodeMap = new HashMap<>();
                            Map<String, Object> subData = (Map<String, Object>) result;
                            if (subData != null && subData.get("data") != null && subData.get("data") instanceof Object[]) {
                                Object[] dataList = (Object[]) subData.get("data");
                                for (Object dataItem : dataList) {
                                    Map<String, String> item = (Map<String, String>) dataItem;
                                    if (!item.get("SubFormat").equals("srt")) {
                                        continue;
                                    }

                                    // episode check
                                    if (Integer.parseInt(item.get("SeriesIMDBParent")) != Integer.parseInt(episode.imdbId.replace("tt", ""))) {
                                        continue;
                                    }
                                    if (!item.get("SeriesSeason").equals(seasonStr)) {
                                        continue;
                                    }
                                    if (!item.get("SeriesEpisode").equals(episodeStr)) {
                                        continue;
                                    }

                                    String url = item.get("SubDownloadLink").replace(".gz", ".srt");
                                    String lang = item.get("ISO639").replace("pb", "pt-br");
                                    int downloads = Integer.parseInt(item.get("SubDownloadsCnt"));
                                    int score = 0;

                                    if (item.get("MatchedBy").equals("tag")) {
                                        score += 50;
                                    }
                                    if (item.get("UserRank").equals("trusted")) {
                                        score += 100;
                                    }
                                    if (!episodeMap.containsKey(lang)) {
                                        episodeMap.put(lang, url);
                                        scoreMap.put(lang, new Integer[]{score, downloads});
                                    } else if (score > scoreMap.get(lang)[0] || (score == scoreMap.get(lang)[0] && downloads > scoreMap.get(lang)[1])) {
                                        episodeMap.put(lang, url);
                                        scoreMap.put(lang, new Integer[]{score, downloads});
                                    }
                                }
                                returnMap.put(episode.videoId, episodeMap);
                                callback.onSuccess(returnMap.get(episode.videoId));
                            } else {
                                callback.onFailure(new XMLRPCException("No subs found"));
                            }
                        }

                        @Override
                        public void onError(long id, XMLRPCException error) {
                            callback.onFailure(error);
                        }

                        @Override
                        public void onServerError(long id, XMLRPCServerException error) {
                            callback.onFailure(error);
                        }
                    });
                } else {
                    callback.onFailure(new XMLRPCException("Token not correct"));
                }
            }

            @Override
            public void onError(long id, XMLRPCException error) {
                callback.onFailure(error);
            }

            @Override
            public void onServerError(long id, XMLRPCServerException error) {
                callback.onFailure(error);
            }
        });
    }

    /**
     * Login to server and get token
     *
     * @return Token
     */
    private void login(XMLRPCCallback callback) {
        try {
            XMLRPCClient client = new XMLRPCClient(new URL(mApiUrl.replace("http://", "https://")), mUserAgent);
            client.callAsync(callback, "LogIn", "", "", "en", mUserAgent);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            // Just catch and fail
        }
    }

    /**
     * Search for subtitles by imdbId, season and episode
     *
     * @param episode Episode
     * @param token   Login token
     * @return SRT URL
     */
    private void search(Episode episode, String token, XMLRPCCallback callback) {
        try {
            XMLRPCClient client = new XMLRPCClient(new URL(mApiUrl), mUserAgent);
            Map<String, String> option = new HashMap<>();
            option.put("imdbid", episode.imdbId.replace("tt", ""));
            option.put("season", String.format(Locale.US, "%d", episode.season));
            option.put("episode", String.format(Locale.US, "%d", episode.episode));
            option.put("sublanguageid", "all");
            client.callAsync(callback, "SearchSubtitles", token, new Object[]{option});
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}