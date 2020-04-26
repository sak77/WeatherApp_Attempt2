package com.saket.weatherapp_attempt2.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.saket.weatherapp_attempt2.model.City;
import com.saket.weatherapp_attempt2.repository.WeatherInfoRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * Created by sshriwas on 2020-04-02
 * Viewmodel provides data for the UI. It acts as a glue between UI and data repository
 */
public class CityViewModel extends ViewModel {

    private MutableLiveData<List<City>> mListCityLiveData = new MutableLiveData<>();
    private MutableLiveData<City> mSelectedCityLiveData = new MutableLiveData<>();
    private WeatherInfoRepository mWeatherInfoRepository;
    private static final String TAG = "Cityviewmodel";

    public CityViewModel(WeatherInfoRepository weatherInfoRepository) {
        this.mWeatherInfoRepository = weatherInfoRepository;
    }

    //Get current city info
    public void getCurrentCityWeatherInfo(String[] arrCityNames) {
        final List<City> lstCities = new ArrayList<>();
        final List<Observable<City>> observables = new ArrayList<>();
        /*
        I was trying to use observables.fromarray here instead of for loop. But it simply does
        not work. Reason being observables.fromarray executes on a separate thread and the control
        moves forward until it receives the emits. Also each emit then needs to be flatmapped to
        a city observable. This was proving difficult function input was string and output was city.
         */
        for (String s: arrCityNames) {
            //Create observable
            Observable<City> observable = mWeatherInfoRepository.getCityInfo(s)
                    .flatMap(
                            (Function<List<City>, ObservableSource<City>>) cities -> {
                                //Get city
                                final City city = cities.get(0);
                                return mWeatherInfoRepository.updateCityWeatherInfo(city);
                            });
            //add observable to list
            observables.add(observable);
        }

        //merge observables and emit items
        Observable.merge(observables)
                .subscribe(new Observer<City>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(City city) {
                        Log.d(TAG, "onNext: " + city.getTitle());
                        lstCities.add(city);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                        //Update UI
                        mListCityLiveData.postValue(lstCities);
                    }
                });

        //Zip is not really required here. Merge works better
                /*Observable.zip(observables, objects -> objects)
                .subscribe(new Observer<Object[]>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("TAG", "onSubscribe: ");
                    }

                    @Override
                    public void onNext(Object[] objects) {
                        Log.d("TAG", "onNext: ");
                        for (int i = 0; i < objects.length; i++) {
                            City currCity = (City) objects[i];
                            Log.d("TAG", "onNext: " + currCity.getTitle());
                            lstCities.add(currCity);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onError: ", e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d("TAG", "onComplete: ");
                        //update live data here.
                    }
                });*/
    }

    public MutableLiveData<List<City>> getListCityLiveData() {
        return mListCityLiveData;
    }

    public MutableLiveData<City> getSelectedCityLiveData() {
        return mSelectedCityLiveData;
    }

    public void setSelectedCityLiveData(City selectedCity) {
        mSelectedCityLiveData.setValue(selectedCity);
    }
}
