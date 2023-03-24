package com.example.weather.model

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.mvvn.network.RemoteSource
import com.example.weather.database.LocalSource


class Repository private constructor(var remoteSource: RemoteSource,
                                     var localSource: LocalSource,
                                     var context: Context)
    : RepositoryInterface {
    companion object {
        private var instance: Repository? = null
        fun getInstance(
            remoteSource: RemoteSource,
            localSource: LocalSource,
            context: Context
        ): Repository {
            return instance ?: Repository(
                remoteSource,localSource ,context
            )
        }
    }
    override suspend fun getCurrentLocationWeather(lat:String,lon:String,lang:String,apiKey:String): WeatherModel {
        val currentWeather= remoteSource.getCurrentWeather(lat,lon,lang,apiKey)
        println("bosy da eli wasali delwa2ty:"+currentWeather.current.weather.get(0).id)
        return currentWeather
    }


//    override fun insertWeather(weatherInfo: LocalCurrentWeatherModel) {
//        localSource.insertWeather(weatherInfo)
//    }
//
//    override fun deleteAll() {
//        localSource.deleteAll()
//    }
//
//    override fun getLocalWeather(latLon: String): LiveData<LocalCurrentWeatherModel> {
//        Log.i("TAG","from repo ${localSource.getLocalWeather(latLon).value?.address}")
//        return localSource.getLocalWeather(latLon)
//    }
//
//    override val storedFavWeather: LiveData<List<FavModel>>
//        get() = localSource.allStoredFavWeather
//    override fun insertFavWeather(favInfo: FavModel) {
//        localSource.insertFavWeather(favInfo)
//
//    }
//
//    override fun deleteFavWeather(favInfo: FavModel) {
//        localSource.deleteFavInfo(favInfo)
//    }
//
//    override fun getFavItemWeather(latLon: String): LiveData<FavModel> {
//        return localSource.getFavItemWeather(latLon)
//    }
//
//    override val storedAlertsWeather: LiveData<List<CustomAlert>>
//        get() = localSource.allStoredAlertsWeather
//
//    override fun deleteAlertWeather(customAlert: CustomAlert) {
//        localSource.deleteAlertInfo(customAlert)
//    }
//
//    override fun insertAlertWeather(id:String, address:String, lon:String, lan:String, dates:String,
//                                    time:String) {
//        val customAlert=CustomAlert(id,address,lon,lan,dates,time)
//        localSource.insertAlertWeather(customAlert)
//    }


}