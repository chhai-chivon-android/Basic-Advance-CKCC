package com.nuon.peter.demoapp.ui.home.entity.movies.deserialize;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.nuon.peter.demoapp.ui.home.entity.movies.Data;
import com.nuon.peter.demoapp.ui.home.entity.movies.MoviesItem;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by manithnuon on 2/5/17.
 */

public class ResponseMoviesListDeserialize implements JsonDeserializer<ResponseMoviesList> {
  @Override public ResponseMoviesList deserialize(JsonElement json, Type typeOfT,
      JsonDeserializationContext context) throws JsonParseException {
    Gson gson = new Gson();
    ResponseMoviesList loginResponse = gson.fromJson(json, ResponseMoviesList.class);
    Log.d("Gson:::",loginResponse.toString());
    final JsonObject jsonObject = json.getAsJsonObject();
    final JsonArray element = jsonObject.getAsJsonObject("data").getAsJsonArray("movies");
    if(element.isJsonArray()){
      Type movieListType = new TypeToken<List<MoviesItem>>() {}.getType();
      loginResponse.setMovies((List<MoviesItem>) gson.fromJson(element,movieListType));
    }
    return loginResponse;
  }
}
