package com.saket.weatherapp_attempt2.repository;

import com.saket.weatherapp_attempt2.model.City;
import com.saket.weatherapp_attempt2.model.WeatherInfo;
import com.saket.weatherapp_attempt2.network.APIRetrofitClient;
import com.saket.weatherapp_attempt2.network.Weatherapiservice;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by sshriwas on 2020-03-28
 * Used to perform actual network api operations
 */
public class WeatherInfoRepository {

    public static final String TAG = "WeatherInfoRepository";

    public Observable<List<City>> getCityInfo(String cityName) {
        //Get instance of Retrofit client
        Retrofit retrofit = APIRetrofitClient.getRetrofitInstance();
        Weatherapiservice weatherapiservice = retrofit.create(Weatherapiservice.class);
        return weatherapiservice.getCityInfo(cityName)
                .subscribeOn(Schedulers.io());
    }


    public Observable<City> updateCityWeatherInfo(City currCity) {
        Retrofit retrofit = APIRetrofitClient.getRetrofitInstance();
        Weatherapiservice weatherapiservice = retrofit.create(Weatherapiservice.class);
        String woeid = currCity.getWoeid();

        Disposable d = weatherapiservice.getTomorrowsWeather(woeid, "2020/04/03")
                //.subscribeOn(Schedulers.io())
                .subscribe(weatherInfos -> {
                    WeatherInfo weatherInfo = weatherInfos.get(0);
                    currCity.setWeatherInfo(weatherInfo);
                });
        return Observable.just(currCity);
    }
}
