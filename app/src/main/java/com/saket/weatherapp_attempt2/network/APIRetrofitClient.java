package com.saket.weatherapp_attempt2.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sshriwas on 2020-04-09
 */
public class APIRetrofitClient {

    private static Retrofit mRetrofit;
    private static final String BASE_URL = "https://www.metaweather.com/api/";

    private APIRetrofitClient() {}

    public static Retrofit getRetrofitInstance() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}
