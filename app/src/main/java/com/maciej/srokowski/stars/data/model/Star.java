package com.maciej.srokowski.stars.data.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by maciek on 23.11.17.
 */
@AutoValue
public abstract class Star {

    public abstract String name();

    public abstract String description();

    public abstract String dob();

    public abstract String country();

    public abstract String height();

    public abstract String spouse();

    public abstract String children();

    public abstract String image();

    public static Star create(String name, String description, String dob, String country, String height, String spouse, String children, String image) {
        return new AutoValue_Star(name, description, dob, country, height, spouse, children, image);
    }

    public static TypeAdapter<Star> typeAdapter(Gson gson) {
        return new AutoValue_Star.GsonTypeAdapter(gson);
    }
}
