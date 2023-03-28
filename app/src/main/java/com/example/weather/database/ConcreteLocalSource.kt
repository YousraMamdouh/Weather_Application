package com.example.weather.database

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.weather.model.WeatherModel


class  ConcreteLocalSource (context: Context
) : LocalSource {
    private val dao:WeatherDao by lazy {
        val db:WeatherDatabase= WeatherDatabase.getInstance(context)
        db.getProductsDAO()
    }

    override  suspend fun getStoredCurrentWeatherObject(): WeatherModel {
 return dao.getStoredCurrentWeather()
    }

    override suspend fun insertCurrentWeatherObject(weatherObject: WeatherModel) {
   dao.insertCurrentWeatherObject(weatherObject)
    }


}