package com.nuon.peter.demoapp.model.movies.deserialize;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.nuon.peter.demoapp.model.movies.Data;
import com.nuon.peter.demoapp.model.movies.ResponseMovies;
import java.lang.reflect.Type;

/**
 * Created by manithnuon on 2/4/17.
 */

public class ResponseMovieDeserialize implements JsonDeserializer<ResponseData> {
  @Override public ResponseData deserialize(JsonElement json, Type typeOfT,
      JsonDeserializationContext context) throws JsonParseException {
    Gson gson = new Gson();
    ResponseData loginResponse = gson.fromJson(json, ResponseData.class);
    Log.d("Gson:::",loginResponse.toString());
    final JsonObject jsonObject = json.getAsJsonObject();
    final JsonElement element = jsonObject.get("data");
    Data data = gson.fromJson(element,Data.class);
    loginResponse.setData(data);
    return loginResponse;
  }
}
