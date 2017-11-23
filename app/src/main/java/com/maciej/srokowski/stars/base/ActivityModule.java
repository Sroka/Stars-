package com.maciej.srokowski.stars.base;

import com.maciej.srokowski.stars.features.stars.StarsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by maciek on 23.11.17.
 */
@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract StarsActivity contributeStarsActivity();
}
