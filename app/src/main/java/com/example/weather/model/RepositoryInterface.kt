package com.example.weather.model

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RepositoryInterface {
    suspend fun getCurrentLocationWeather(lat:String,lon:String,lang:String,apiKey:String,unit:String):Flow<Response<WeatherModel>>
    suspend fun getStoredCurrentWeatherObjectFromDatabase():WeatherModel

    suspend fun insertCurrentWeatherObject(weatherObject:WeatherModel)


//    //Store weather info
//    fun insertWeather(weatherInfo: LocalCurrentWeatherModel)
//    fun deleteAll()
//    fun getLocalWeather(latLon:String): LiveData<LocalCurrentWeatherModel>


    //fav


    suspend fun getStoredFavorites(): Flow<List<FavoriteModel>>?
  suspend  fun insertToFavorites(favInfo: FavoriteModel)
   suspend fun deleteFromFavorites(favInfo: FavoriteModel)



//    //alert
//    val storedAlertsWeather: LiveData<List<CustomAlert>>
//    fun deleteAlertWeather(customAlert: CustomAlert)
//    fun insertAlertWeather(id:String, address:String, lon:String, lan:String, dates:String,
//                           time:String)


}