package com.maciej.srokowski.stars.features.stars;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.maciej.srokowski.stars.domain.GetStarsUseCase;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by maciek on 23.11.17.
 */

@Singleton
public class StarsViewModelFactory implements ViewModelProvider.Factory {

    private final GetStarsUseCase getStarsUseCase;

    @Inject
    public StarsViewModelFactory(GetStarsUseCase getStarsUseCase) {
        this.getStarsUseCase = getStarsUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(StarsViewModel.class)) {
            return (T) new StarsViewModel(getStarsUseCase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
