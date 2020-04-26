package com.saket.weatherapp_attempt2.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.saket.weatherapp_attempt2.repository.WeatherInfoRepository;

/**
 * Created by sshriwas on 2020-04-02
 */
public class CityViewModelFactory {

    public static CityViewModel getInstance(ViewModelStoreOwner owner) {
        return new ViewModelProvider(owner, new MyFactory()).get(CityViewModel.class);
    }

    private static class MyFactory implements ViewModelProvider.Factory {

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            //provide external dependencies
            WeatherInfoRepository weatherInfoRepository = new WeatherInfoRepository();
            return (T) new CityViewModel(weatherInfoRepository);
        }
    }
}
