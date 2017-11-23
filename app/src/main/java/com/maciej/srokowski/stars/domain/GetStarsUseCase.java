package com.maciej.srokowski.stars.domain;

import com.maciej.srokowski.stars.data.model.Star;
import com.maciej.srokowski.stars.data.repositories.StarsRepository;
import com.maciej.srokowski.stars.domain.util.RequestState;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by maciek on 23.11.17.
 */
@Singleton
public class GetStarsUseCase {

    private final StarsRepository starsRepository;

    @Inject
    public GetStarsUseCase(StarsRepository starsRepository) {
        this.starsRepository = starsRepository;
    }

    /**
     * Wrappign response from the source in response digestable by view. For such simple use case
     * it doesn't make sense but if we have some real-time data to handle or Authentications
     * errors there can be some fancy logic involved, its for the best to handle it in domain layer.
     *
     * @return RequestState with GREATEST stars in the world or some sad, sad error
     */
    public Single<RequestState<List<Star>>> getStars() {
        return starsRepository.getStars()
                .map(RequestState::success)
                .onErrorReturn(RequestState::error);
    }
}
