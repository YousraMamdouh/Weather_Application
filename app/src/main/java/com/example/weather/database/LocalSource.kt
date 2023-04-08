package com.example.weather.database

import androidx.lifecycle.LiveData
import com.example.weather.model.FavoriteModel
import com.example.weather.model.WeatherModel
import kotlinx.coroutines.flow.Flow


interface LocalSource {
//    fun insertWeather(weatherModel: LocalCurrentWeatherModel)
//    fun deleteAll()
//    fun getLocalWeather(latLon:String): LiveData<LocalCurrentWeatherModel>
//
//    fun insertFavWeather(favModel: FavModel)
//    val allStoredFavWeather: LiveData<List<FavModel>>
//    fun deleteFavInfo(favModel: FavModel)
//    fun getFavItemWeather(latLon:String): LiveData<FavModel>
//
//    val allStoredAlertsWeather: LiveData<List<CustomAlert>>
//    fun deleteAlertInfo(customAlert: CustomAlert)
//    fun insertAlertWeather(customAlert: CustomAlert)

    //current weather
     suspend fun getStoredCurrentWeatherObject():WeatherModel

    suspend fun insertCurrentWeatherObject(weatherObject:WeatherModel)

    //favorites
//    val allStoredFavorites: LiveData<List<FavoriteModel>>
//    fun insertToFavorites(favoriteModel: FavoriteModel)
//    fun deleteFromFavorites(favoriteModel: FavoriteModel)

    suspend fun insertToFavorites(favoriteModel: FavoriteModel)
    suspend fun deleteFromFavorites(favoriteModel: FavoriteModel)
    suspend fun getAllStoredFavorites(): Flow<List<FavoriteModel>>
}