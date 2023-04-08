package com.example.weather.network


import com.example.weather.model.WeatherModel
import retrofit2.Response

sealed class ApiState{
    class Success (val data:WeatherModel):ApiState()
    class Failure(val msg:Throwable):ApiState()
    object Loading:ApiState()
}
