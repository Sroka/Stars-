package com.maciej.srokowski.stars.domain;

import com.maciej.srokowski.stars.data.model.Star;
import com.maciej.srokowski.stars.data.model.StarsResponse;
import com.maciej.srokowski.stars.data.network.StarsNetworkSource;
import com.maciej.srokowski.stars.data.repositories.StarsRepository;
import com.maciej.srokowski.stars.domain.util.RequestState;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

/**
 * Created by maciek on 23.11.17.
 */
public class GetStarsUseCaseTest {

    private static final String STAR_A_VALUES = "a";
    private static final String STAR_B_VALUES = "b";

    @Test
    public void getStarsTest_testContent() {
        //We could use mockito or other mocking framework but for simple cases it is better to avoid it
        Star starA = Star.create(STAR_A_VALUES, STAR_A_VALUES, STAR_A_VALUES, STAR_A_VALUES,
                STAR_A_VALUES, STAR_A_VALUES, STAR_A_VALUES, STAR_A_VALUES);
        Star starB = Star.create(STAR_B_VALUES, STAR_B_VALUES, STAR_B_VALUES, STAR_B_VALUES,
                STAR_B_VALUES, STAR_B_VALUES, STAR_B_VALUES, STAR_B_VALUES);
        StarsResponse starsResponse = StarsResponse.create(Arrays.asList(starA, starB));
        StarsNetworkSource starsNetworkSource = new StarsNetworkSource() {
            @Override
            public Single<StarsResponse> getStars() {
                return Single.just(starsResponse);
            }
        };
        StarsRepository starsRepository = new StarsRepository(starsNetworkSource);
        GetStarsUseCase getStarsUseCase = new GetStarsUseCase(starsRepository);

        TestObserver<RequestState<List<Star>>> testObserver = new TestObserver<>();
        getStarsUseCase.getStars()
                .subscribe(testObserver);

        //We could provide schedulers with DI instead but it is outside of a scope of this exercise
        testObserver.awaitTerminalEvent();
        testObserver.assertValue(RequestState.success(Arrays.asList(starA, starB)));
    }

    @Test
    public void getStarsTest_testError() {
        //We could use mockito or other mocking framework but for simple cases it is better to avoid it
        IllegalArgumentException exception = new IllegalArgumentException();
        StarsNetworkSource starsNetworkSource = new StarsNetworkSource() {
            @Override
            public Single<StarsResponse> getStars() {

                return Single.error(exception);
            }
        };
        StarsRepository starsRepository = new StarsRepository(starsNetworkSource);
        GetStarsUseCase getStarsUseCase = new GetStarsUseCase(starsRepository);

        TestObserver<RequestState<List<Star>>> testObserver = new TestObserver<>();
        getStarsUseCase.getStars()
                .subscribe(testObserver);

        //We could provide schedulers with DI instead but it is outside of a scope of this exercise
        testObserver.awaitTerminalEvent();
        testObserver.assertValue(RequestState.error(exception));

    }

}