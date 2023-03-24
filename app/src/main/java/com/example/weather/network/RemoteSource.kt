package com.example.mvvn.network

import com.example.weather.model.WeatherModel


interface RemoteSource {
  //  suspend fun getProductsOverNetwork():List<Products>
  suspend fun getCurrentWeather(lat:String,lon:String,lang:String,id:String): WeatherModel

}