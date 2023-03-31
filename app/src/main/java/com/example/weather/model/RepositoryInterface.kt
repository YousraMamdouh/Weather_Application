package com.example.weather.model

import androidx.lifecycle.LiveData

interface RepositoryInterface {
    suspend fun getCurrentLocationWeather(lat:String,lon:String,lang:String,apiKey:String):WeatherModel
    suspend fun getStoredCurrentWeatherObjectFromDatabase():WeatherModel

    suspend fun insertCurrentWeatherObject(weatherObject:WeatherModel)


//    //Store weather info
//    fun insertWeather(weatherInfo: LocalCurrentWeatherModel)
//    fun deleteAll()
//    fun getLocalWeather(latLon:String): LiveData<LocalCurrentWeatherModel>


    //fav
    val allStoredFavorites: LiveData<List<FavoriteModel>>
    fun insertToFavorites(favInfo: FavoriteModel)
    fun deleteFromFavorites(favInfo: FavoriteModel)



//    //alert
//    val storedAlertsWeather: LiveData<List<CustomAlert>>
//    fun deleteAlertWeather(customAlert: CustomAlert)
//    fun insertAlertWeather(id:String, address:String, lon:String, lan:String, dates:String,
//                           time:String)


}