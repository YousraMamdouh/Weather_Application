package com.example.weather.network

import ApiService
import com.example.mvvn.network.RemoteSource
import com.example.weather.model.WeatherModel


class WeatherClient private constructor(): RemoteSource {
    private val weatherService: ApiService by lazy {
        RetrofitHelper.retrofitInstance.create(ApiService::class.java)
    }



    companion object{
        private var instance: WeatherClient?=null
        fun getInstance(): WeatherClient {
            return instance?: synchronized(this)
            {
                val temp= WeatherClient()
                instance=temp
                temp
            }
        }
    }

    override suspend fun getCurrentWeather(
        lat: String,
        lon: String,
        lang: String,
        id: String,
    ): WeatherModel {
        val response=weatherService.getCurrentWeather(lat,lon,lang,id)
        return response
    }
}