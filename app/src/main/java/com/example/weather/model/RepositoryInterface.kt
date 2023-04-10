package com.example.weather.model

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Response

interface RepositoryInterface {
    suspend fun getCurrentLocationWeather(lat:String,lon:String,lang:String,apiKey:String,unit:String): StateFlow<WeatherModel>
    suspend fun getStoredCurrentWeatherObjectFromDatabase():WeatherModel

    suspend fun insertCurrentWeatherObject(weatherObject:WeatherModel)



    //fav


    suspend fun getStoredFavorites(): Flow<List<FavoriteModel>>?
  suspend  fun insertToFavorites(favInfo: FavoriteModel)
   suspend fun deleteFromFavorites(favInfo: FavoriteModel)



    //Alerts

    suspend fun insertToAlerts(alertModel: AlertsModel):Long
    suspend fun deleteFromAlerts(id:Int)
    suspend fun getAllStoredAlerts(): Flow<List<AlertsModel>>
    suspend fun getAlert(id:Int):AlertsModel


}