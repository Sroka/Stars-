package com.maciej.srokowski.stars.features.stars;

import android.arch.lifecycle.ViewModel;

import com.maciej.srokowski.stars.data.model.Star;
import com.maciej.srokowski.stars.domain.GetStarsUseCase;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.processors.BehaviorProcessor;

/**
 * Created by maciek on 23.11.17.
 */

public class StarsViewModel extends ViewModel {

    private final GetStarsUseCase getStarsUseCase;

    private final CompositeDisposable disposables = new CompositeDisposable();

    private final BehaviorProcessor<List<Star>> stars = BehaviorProcessor.createDefault(Collections.emptyList());
    private final BehaviorProcessor<Boolean> progress = BehaviorProcessor.createDefault(false);
    private final BehaviorProcessor<Boolean> error = BehaviorProcessor.createDefault(false);

    public StarsViewModel(GetStarsUseCase getStarsUseCase) {
        this.getStarsUseCase = getStarsUseCase;
    }

    public Observable<List<Star>> getStars() {
        return stars.toObservable();
    }

    public Observable<Boolean> getProgress() {
        return progress.toObservable();
    }

    public Observable<Boolean> getError() {
        return error.toObservable();
    }

    public void loadStars() {
        disposables.add(getStarsUseCase.getStars()
                .doOnSubscribe(disposable -> {
                    error.onNext(false);
                    progress.onNext(true);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listResponse -> {
                    switch (listResponse.status) {
                        case SUCCESS:
                            stars.onNext(listResponse.data);
                            progress.onNext(false);
                            error.onNext(false);
                            break;
                        case ERROR:
                            stars.onNext(Collections.emptyList());
                            progress.onNext(false);
                            error.onNext(true);
                            break;
                    }
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
