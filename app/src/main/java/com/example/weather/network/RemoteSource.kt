package com.example.mvvn.network

import com.example.weather.model.WeatherModel
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Response


interface RemoteSource {
  //  suspend fun getProductsOverNetwork():List<Products>
  suspend fun getCurrentWeather(lat:String,lon:String,lang:String,id:String,unit:String): StateFlow<WeatherModel>

}