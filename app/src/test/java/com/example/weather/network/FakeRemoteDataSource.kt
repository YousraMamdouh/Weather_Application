package com.example.weather.network

import com.example.mvvn.network.RemoteSource
import com.example.weather.model.WeatherModel
import retrofit2.Response

class FakeRemoteDataSource(var weatherResponse:WeatherModel=WeatherModel()):RemoteSource {


    override suspend fun getCurrentWeather(
        lat: String,
        lon: String,
        lang: String,
        id: String,
        unit: String,
    ): Response<WeatherModel> {
      return Response.success(weatherResponse)
    }
}