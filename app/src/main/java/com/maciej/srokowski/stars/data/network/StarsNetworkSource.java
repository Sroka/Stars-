package com.maciej.srokowski.stars.data.network;

import com.maciej.srokowski.stars.data.model.StarsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by maciek on 23.11.17.
 */

public interface StarsNetworkSource {

    @GET("JSONParsingTutorial/jsonActors")
    Single<StarsResponse> getStars();
}
