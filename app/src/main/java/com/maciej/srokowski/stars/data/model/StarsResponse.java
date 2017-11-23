package com.maciej.srokowski.stars.data.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

/**
 * Created by maciek on 23.11.17.
 */

@AutoValue
public abstract class StarsResponse {

    public abstract List<Star> actors();

    public static StarsResponse create(List<Star> actors) {
        return new AutoValue_StarsResponse(actors);
    }

    public static TypeAdapter<StarsResponse> typeAdapter(Gson gson) {
        return new AutoValue_StarsResponse.GsonTypeAdapter(gson);
    }
}
