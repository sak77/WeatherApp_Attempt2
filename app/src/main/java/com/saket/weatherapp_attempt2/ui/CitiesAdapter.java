package com.saket.weatherapp_attempt2.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saket.weatherapp_attempt2.R;
import com.saket.weatherapp_attempt2.model.City;

import java.util.List;

/**
 * Created by sshriwas on 2020-03-28
 */
public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CityViewHolder> {

    private List<City> mCities;

    public interface CityClickListener {
        void onCityClicked(City city);
    }

    private CityClickListener mCityClickListener;

    CitiesAdapter(List<City> lstCities, CityClickListener cityClickListener) {
        this.mCities = lstCities;
        this.mCityClickListener = cityClickListener;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_weather_list_item,
                parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        holder.bind(mCities.get(position), mCityClickListener);
    }

    @Override
    public int getItemCount() {
        return mCities.size();
    }

    static class CityViewHolder extends RecyclerView.ViewHolder {

        TextView txtCityName;
        TextView txtMaxTemp;
        TextView txtMinTemp;

        CityViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCityName = itemView.findViewById(R.id.txtCityName);
            txtMaxTemp = itemView.findViewById(R.id.txtMaxTemp);
            txtMinTemp = itemView.findViewById(R.id.txtMinTemp);
        }

        void bind(City city, CityClickListener cityClickListener) {
            txtCityName.setText(city.getTitle());
            //Benefits of using string.value of instead of "" + ?
            txtMaxTemp.setText("Max: " + city.getWeatherInfo().getMax_temp());
            txtMinTemp.setText("Min: " + city.getWeatherInfo().getMin_temp());
            itemView.setOnClickListener(view -> cityClickListener.onCityClicked(city));
        }
    }
}
