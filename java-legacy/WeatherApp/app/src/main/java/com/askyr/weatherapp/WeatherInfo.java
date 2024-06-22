package com.askyr.weatherapp;

import com.google.gson.annotations.SerializedName;

// This is the corresponding java class to the json object which contains all the weather data provided with each request
public class WeatherInfo {

    @SerializedName("main")
    private MainWeatherData mainWeatherData;

    public MainWeatherData getMainWeatherData() {
        return mainWeatherData;
    }
}
