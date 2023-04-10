package com.example.weather.database

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather.model.AlertsModel
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


    //Alerts

    suspend fun insertToAlerts(alertModel: AlertsModel):Long
    suspend fun deleteFromAlerts(id:Int)
    suspend fun getAllStoredAlerts(): Flow<List<AlertsModel>>
    suspend fun getAlert(id:Int):AlertsModel







}