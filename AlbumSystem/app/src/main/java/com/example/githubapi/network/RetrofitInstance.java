package com.example.githubapi.network;

import android.content.Context;

import com.example.githubapi.network.Interceptors.LoggingInterceptor;
import com.example.githubapi.network.Interceptors.ResponseInterceptor;
import com.example.githubapi.utils.Constants;


import okhttp3.Cache;
import okhttp3.OkHttpClient;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(Context context) {
        if (retrofit == null) {
            int cacheSize = 10 * 1024 * 1024; // 10 MB
            Cache cache = new Cache(context.getCacheDir(), cacheSize);

            //Log the information sent to the server
            OkHttpClient client = new OkHttpClient
                    .Builder()
                    .addInterceptor(new LoggingInterceptor())
                    .addInterceptor(new ResponseInterceptor(context))
                    .cache(cache)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
