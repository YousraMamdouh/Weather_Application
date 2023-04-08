package com.example.weather.database

import androidx.lifecycle.LiveData
import com.example.weather.model.FavoriteModel
import com.example.weather.model.WeatherModel
import kotlinx.coroutines.flow.Flow


interface LocalSource {

//    val allStoredAlertsWeather: LiveData<List<CustomAlert>>
//    fun deleteAlertInfo(customAlert: CustomAlert)
//    fun insertAlertWeather(customAlert: CustomAlert)

    //current weather
     suspend fun getStoredCurrentWeatherObject():WeatherModel

    suspend fun insertCurrentWeatherObject(weatherObject:WeatherModel)

    //favorites


    suspend fun insertToFavorites(favoriteModel: FavoriteModel)
    suspend fun deleteFromFavorites(favoriteModel: FavoriteModel)
    suspend fun getAllStoredFavorites(): Flow<List<FavoriteModel>>
}