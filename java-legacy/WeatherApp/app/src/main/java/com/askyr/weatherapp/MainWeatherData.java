package com.askyr.weatherapp;

import com.google.gson.annotations.SerializedName;

// This is the nested java class corresponding to the "main"-json object which contains the key value pairs for the required data of our interest
public class MainWeatherData {

    // this serialized name will help retrofit to bind this field with the "temp" field in the "main" nested json object
    @SerializedName("temp")
    private Double temperature;

    // this serialized name will help retrofit to bind this field with the "pressure" field in the "main" nested json object
    @SerializedName("pressure")
    private String pressure;

    public Double getTemperature() {
        return temperature;
    }

    public String getPressure() {
        return pressure;
    }
}
