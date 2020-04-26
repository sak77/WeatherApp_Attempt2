package com.saket.weatherapp_attempt2.ui;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.saket.weatherapp_attempt2.MainActivity;
import com.saket.weatherapp_attempt2.R;
import com.saket.weatherapp_attempt2.databinding.FragmentWeatherDetailsBinding;
import com.saket.weatherapp_attempt2.model.City;
import com.saket.weatherapp_attempt2.viewmodel.CityViewModelFactory;

/**
 * Created by sshriwas on 2020-03-28
 * Displays tomorrow's weather for given city
 */
public class WeatherDetailFragment extends Fragment {

    static WeatherDetailFragment newInstance() {

        Bundle args = new Bundle();

        WeatherDetailFragment fragment = new WeatherDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        //View root = inflater.inflate(R.layout.fragment_weather_details, container, false);
        //TextView txtWindSpeed = root.findViewById(R.id.txtWindSpeed);
        //TextView txtWindDirection = root.findViewById(R.id.txtWindDirection);
        //TextView txtHumidity = root.findViewById(R.id.txtHumidity);
        FragmentWeatherDetailsBinding detailsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_weather_details,container,
                false);
        City myCity = new City();
        myCity.setTitle("My Title");
        //detailsBinding.setCity(myCity);
        //Populate data from selected city
        CityViewModelFactory.getInstance(requireActivity())
                .getSelectedCityLiveData()
                .observe(getViewLifecycleOwner(), new Observer<City>() {
                    @Override
                    public void onChanged(City city) {
                        detailsBinding.setCity(city);
                        //txtWindSpeed.setText("Wind speed: " + city.getWeatherInfo().getWind_speed());
                        //txtWindDirection.setText("Wind speed: " + city.getWeatherInfo().getWind_direction());
                        //txtHumidity.setText("Wind speed: " + city.getWeatherInfo().getHumidity());
                    }
                });
        return detailsBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Display home icon
        ((MainActivity)getActivity()).displayBackArrow();
    }

}
