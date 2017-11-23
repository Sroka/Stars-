package com.maciej.srokowski.stars.base;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by maciek on 23.11.17.
 */
@Singleton
@Component(modules = {ApplicationModule.class, ActivityModule.class, AndroidInjectionModule.class})
public interface ApplicationComponent {
    void inject(StarsApp app);
}