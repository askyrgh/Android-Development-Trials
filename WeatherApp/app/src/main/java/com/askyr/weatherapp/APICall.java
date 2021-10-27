package com.askyr.weatherapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APICall {

    @GET("weather")
    Call<WeatherInfo> getWeatherData(@Query("q") String city, @Query("appid") String apiKey);
}
