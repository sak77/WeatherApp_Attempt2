package com.saket.weatherapp_attempt2.ui;

import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saket.weatherapp_attempt2.R;
import com.saket.weatherapp_attempt2.model.City;
import com.saket.weatherapp_attempt2.viewmodel.CityViewModel;
import com.saket.weatherapp_attempt2.viewmodel.CityViewModelFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sshriwas on 2020-03-28
 * Displays list of cities
 */
public class MasterListFragment extends Fragment implements CitiesAdapter.CityClickListener {

    RecyclerView mLstCities;
    CityViewModel mCityViewModel;
    List<City> mCities;
    CitiesAdapter mCitiesAdapter;
    private static final String TAG = "MasterListFragment";

    public static MasterListFragment newInstance() {

        Bundle args = new Bundle();

        MasterListFragment fragment = new MasterListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //Private constructor so that it cannot be invoked elsewhere
    private MasterListFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View root = inflater.inflate(R.layout.fragment_master_list, container, false);
        mLstCities = root.findViewById(R.id.cities_list);
        mCityViewModel = CityViewModelFactory.getInstance(requireActivity());
        mCities = new ArrayList<>();
        mCitiesAdapter = new CitiesAdapter(mCities, MasterListFragment.this);
        mLstCities.setLayoutManager(new LinearLayoutManager(getContext()));
        mLstCities.setAdapter(mCitiesAdapter);

        getCityWeatherInfo();
        return root;
    }

    //setup recyclerview
    private void getCityWeatherInfo() {
        mCityViewModel.getListCityLiveData().observe(
                getViewLifecycleOwner(),
                cities -> {
                    mCities.clear();
                    mCities.addAll(cities);
                    mCitiesAdapter.notifyDataSetChanged();
                });

        String[] arrCityNames = getResources().getStringArray(R.array.cities);
        mCityViewModel.getCurrentCityWeatherInfo(arrCityNames);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated: ");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: ");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    public void onCityClicked(City city) {
        //Update selected city
        mCityViewModel.setSelectedCityLiveData(city);
        //Re-direct to weather details fragment
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, WeatherDetailFragment.newInstance())
        .addToBackStack(null)   //Remember this only saves the transaction to the backstack and not the actual fragment.
        .commit();
    }
}