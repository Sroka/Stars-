package com.maciej.srokowski.stars.data.repositories;

import com.maciej.srokowski.stars.data.model.Star;
import com.maciej.srokowski.stars.data.model.StarsResponse;
import com.maciej.srokowski.stars.data.network.StarsNetworkSource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by maciek on 23.11.17.
 */
@Singleton
public class StarsRepository {

    private final StarsNetworkSource starsNetworkSource;

    @Inject
    public StarsRepository(StarsNetworkSource starsNetworkSource) {
        this.starsNetworkSource = starsNetworkSource;
    }

    /**
     * Normally here we would decide if data comes form network source or cache but since cache is
     * outside of scope for this project it will just pass what it get from network source
     *
     * @return List of world greatest stars
     */
    public Single<List<Star>> getStars() {
        return starsNetworkSource.getStars()
                .subscribeOn(Schedulers.io())
                .map(StarsResponse::actors);
    }
}
