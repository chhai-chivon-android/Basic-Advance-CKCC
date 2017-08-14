package com.nuon.peter.demoapp.base.component.providers.media;

import android.content.Context;
import com.nuon.peter.demoapp.base.component.content.preferences.Prefs;
import com.nuon.peter.demoapp.base.component.providers.BaseProvider;
import com.nuon.peter.demoapp.base.component.providers.media.models.Episode;
import com.nuon.peter.demoapp.base.component.providers.media.models.Media;
import com.nuon.peter.demoapp.base.component.providers.media.models.Movie;
import com.nuon.peter.demoapp.base.component.subs.FatalParsingException;
import com.nuon.peter.demoapp.base.component.subs.FormatASS;
import com.nuon.peter.demoapp.base.component.subs.FormatSRT;
import com.nuon.peter.demoapp.base.component.subs.TimedTextObject;
import com.nuon.peter.demoapp.base.network.OKHttpManager;
import com.nuon.peter.demoapp.utils.FileUtils;
import com.nuon.peter.demoapp.utils.PrefUtils;
import com.nuon.peter.demoapp.utils.StorageUtils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public abstract class SubsProvider extends BaseProvider {
    public static final String SUBS_CALL = "subs_http_call";
    public static final String SUBTITLE_LANGUAGE_NONE = "no-subs";

    private static List<String> SUB_EXTENSIONS = Arrays.asList("srt", "ssa", "ass");

    public abstract void getList(Movie movie, Callback callback);

    public abstract void getList(Episode episode, Callback callback);

    public interface Callback {
        void onSuccess(Map<String, String> items);

        void onFailure(Exception e);
    }

    public static File getStorageLocation(Context context) {
        return new File(PrefUtils.get(context, Prefs.STORAGE_LOCATION, StorageUtils.getIdealCacheDirectory(context).toString()) + "/subs/");
    }

    /**
     * @param context      Context
     * @param media        Media data
     * @param languageCode Code of language
     * @param callback     Network callback
     * @return Call
     */
    public static Call download(final Context context, final Media media, final String languageCode, final okhttp3.Callback callback) {
        OkHttpClient client = OKHttpManager.getInstance();
        if (media.subtitles != null && media.subtitles.containsKey(languageCode)) {
            try {
                Request request = new Request.Builder().url(media.subtitles.get(languageCode)).build();
                Call call = client.newCall(request);

                final File subsDirectory = getStorageLocation(context);
                final String fileName = media.videoId + "-" + languageCode;
                final File srtPath = new File(subsDirectory, fileName + ".srt");

                if (srtPath.exists()) {
                    callback.onResponse(null,null);
                    return call;
                }

                call.enqueue(new okhttp3.Callback() {
                    @Override public void onFailure(Call call, IOException e) {
                        callback.onFailure(call,e);
                    }

                    @Override public void onResponse(Call call, Response response)
                        throws IOException {
                        if (response.isSuccessful()) {
                            InputStream inputStream = null;
                            boolean failure = false;
                            try {


                                subsDirectory.mkdirs();
                                if (srtPath.exists()) {
                                    File to = new File(subsDirectory, "temp" + System.currentTimeMillis());
                                    srtPath.renameTo(to);
                                    to.delete();
                                }

                                inputStream = response.body().byteStream();
                                String urlString = response.request().url().toString();

                                if (urlString.contains(".zip") || urlString.contains(".gz")) {
                                    SubsProvider.unpack(inputStream, srtPath, languageCode);
                                } else if (SubsProvider.isSubFormat(urlString)) {
                                    parseFormatAndSave(urlString, srtPath, languageCode, inputStream);
                                } else {
                                    callback.onFailure(call,new IOException("FatalParsingException"));
                                    failure = true;
                                }
                            } catch (FatalParsingException e) {
                                e.printStackTrace();
                                callback.onFailure(call,new IOException("FatalParsingException"));
                                failure = true;
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                                callback.onFailure(call,e);
                                failure = true;
                            } catch (IOException e) {
                                e.printStackTrace();
                                callback.onFailure(call,e);
                                failure = true;
                            } finally {
                                if (inputStream != null)
                                    inputStream.close();

                                if (!failure) callback.onResponse(call,response);
                            }
                        } else {
                            callback.onFailure(call,new IOException("Unknown error"));
                        }
                    }
                });


                return call;
            } catch (RuntimeException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        callback.onFailure(null, new IOException("Wrong media"));
        return null;
    }

    /**
     * Unpack ZIP and save SRT
     *
     * @param is           InputStream from network
     * @param srtPath      Path where SRT should be saved
     * @param languageCode The language code
     * @throws IOException
     * @throws FatalParsingException
     */
    private static void unpack(InputStream is, File srtPath, String languageCode) throws
        IOException, FatalParsingException {
        String filename;
        ZipInputStream zis = new ZipInputStream(new BufferedInputStream(is));
        ZipEntry ze;

        while ((ze = zis.getNextEntry()) != null) {
            filename = ze.getName();
            if (filename.contains("_MACOSX")) continue;

            if (isSubFormat(filename)) {
                parseFormatAndSave(filename, srtPath, languageCode, zis);
                try {
                    zis.closeEntry();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
        }

        zis.close();
    }

    /**
     * Test if file is subtitle format
     *
     * @param filename Name of file
     * @return is subtitle?
     */
    private static boolean isSubFormat(String filename) {
        for (String ext : SUB_EXTENSIONS) {
            if (filename.contains("." + ext)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Parse the text, convert and save to SRT file
     *
     * @param inputUrl     Original network location
     * @param srtPath      Place where SRT should be saved
     * @param languageCode The language code
     * @param inputStream  InputStream of data  @throws IOException
     */
    private static void parseFormatAndSave(String inputUrl, File srtPath, String languageCode, InputStream inputStream) throws
        IOException {
        TimedTextObject subtitleObject = null;

        String inputString = FileUtils.inputstreamToCharsetString(inputStream, languageCode);
        String[] inputText = inputString.split("\n|\r\n");

        if (inputUrl.contains(".ass") || inputUrl.contains(".ssa")) {
            FormatASS formatASS = new FormatASS();
            subtitleObject = formatASS.parseFile(inputUrl, inputText);
        } else if (inputUrl.contains(".srt")) {
            FormatSRT formatSRT = new FormatSRT();
            subtitleObject = formatSRT.parseFile(inputUrl, inputText);
        }

        if (subtitleObject != null) {
            FileUtils.saveStringFile(subtitleObject.toSRT(), srtPath);
        }
    }
}