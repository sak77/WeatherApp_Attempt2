package com.saket.weatherapp_attempt2.network;

import com.saket.weatherapp_attempt2.model.City;
import com.saket.weatherapp_attempt2.model.WeatherInfo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by sshriwas on 2020-03-28
 * consists of endpoints exposed by metaweather api
 */
public interface Weatherapiservice {

    //Get city info
    @GET("location/search/")
    Observable<List<City>> getCityInfo(@Query("query") String cityName);

    //Get tomorrows weather
    @GET("api/location/{woeid}/{date}/")
    Observable<List<WeatherInfo>> getTomorrowsWeather(@Path("woeid") String woeid, @Path("date") String date);
}
