package com.maciej.srokowski.stars.data.util;

import com.google.gson.TypeAdapterFactory;

@com.ryanharter.auto.value.gson.GsonTypeAdapterFactory
public abstract class GsonTypeAdapterFactory implements TypeAdapterFactory {

    public static GsonTypeAdapterFactory create() {
        return new AutoValueGson_GsonTypeAdapterFactory();
    }

}