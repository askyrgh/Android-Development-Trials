package com.askyr.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView txtInfo;
    private EditText edtCity;
    private Button btnFetch;

    APICall apiCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtInfo = findViewById(R.id.txt_result);
        btnFetch = findViewById(R.id.btn_fetch);
        edtCity = findViewById(R.id.edt_city);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServerInfo.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // This will create the implementation of the API call that we defined
        apiCall = retrofit.create(APICall.class);

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // This generated the request URL that will be sent to retrieve response from the API
                Call<WeatherInfo> call = apiCall.getWeatherData(edtCity.getText().toString(), ServerInfo.API_KEY);

                // Lining up the Call object for the request-response cycle
                call.enqueue(new Callback<WeatherInfo>() {

                    // Here response object is the entire json object that is retrieved along with every request sent
                    @Override
                    public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                        WeatherInfo weatherInfo = response.body();
                        // Reflecting the response result on UI
                        txtInfo.setText("T: " + (weatherInfo.getMainWeatherData().getTemperature() - 273.15)
                                + " C -  P: " + weatherInfo.getMainWeatherData().getPressure() + " atm");
                    }

                    @Override
                    public void onFailure(Call<WeatherInfo> call, Throwable t) {
                        Log.d("debug-weather", "Failed to retrieve response.");
                    }
                });
            }
        });
    }
}