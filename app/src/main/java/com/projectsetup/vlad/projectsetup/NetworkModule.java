package com.projectsetup.vlad.projectsetup;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by vladi on 11/12/2016.
 */
@Module
public class NetworkModule {

    private String mBaseUrl;

    public NetworkModule(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    NetworkApi provideApi(Retrofit retrofit) {
        return retrofit.create(NetworkApi.class);
    }
}
