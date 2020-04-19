package com.example.demoapi.di.modules;

import android.app.Application;

import com.example.demoapi.network.StoreService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private String mBaseApiUrl;

    public NetworkModule(String baseApiUrl) {
        this.mBaseApiUrl = baseApiUrl;
    }

    @Provides
    @Singleton
    Cache provideCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 Mb
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }



    @Provides
    @Singleton
    OkHttpClient provideHttpClient(Cache cache) {
        return new OkHttpClient.Builder()

                .cache(cache)
                .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient client) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())

                .baseUrl(mBaseApiUrl)
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    StoreService provideStoreService(Retrofit retrofit) {
        return retrofit.create(StoreService.class);
    }
}
