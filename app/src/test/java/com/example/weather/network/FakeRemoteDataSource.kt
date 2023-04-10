package com.example.weather.network

import com.example.mvvn.network.RemoteSource
import com.example.weather.model.WeatherModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Response

class FakeRemoteDataSource(var weatherResponse:WeatherModel=WeatherModel()):RemoteSource {


    override suspend fun getCurrentWeather(

        lat: String,
        lon: String,
        lang: String,
        id: String,
        unit: String,
    ): StateFlow<WeatherModel> {

        var stateFlow= MutableStateFlow(WeatherModel()) as StateFlow<WeatherModel>
      return stateFlow
    }
}