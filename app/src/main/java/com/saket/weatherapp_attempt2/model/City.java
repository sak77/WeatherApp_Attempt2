package com.saket.weatherapp_attempt2.model;

/**
 * Created by sshriwas on 2020-03-28
 */
public class City {

    private String title;
    private String woeid;

    private WeatherInfo mWeatherInfo;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWoeid() {
        return woeid;
    }

    public void setWoeid(String woeid) {
        this.woeid = woeid;
    }

    public WeatherInfo getWeatherInfo() {
        return mWeatherInfo;
    }

    public void setWeatherInfo(WeatherInfo weatherInfo) {
        mWeatherInfo = weatherInfo;
    }
}
