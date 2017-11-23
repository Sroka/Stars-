package com.maciej.srokowski.stars.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.maciej.srokowski.stars.data.network.StarsNetworkSource;
import com.maciej.srokowski.stars.data.util.GsonTypeAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by maciek on 23.11.17.
 */
@Module
public class ApplicationModule {

    @Singleton
    @Provides
    Gson gson() {
        return new GsonBuilder()
                .registerTypeAdapterFactory(GsonTypeAdapterFactory.create())
                .create();
    }

    @Singleton
    @Provides
    StarsNetworkSource starsNetworkSource(Gson gson) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        return new Retrofit.Builder()
                .baseUrl("http://microblogging.wingnity.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(StarsNetworkSource.class);
    }
}