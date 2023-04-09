package com.example.weather.model

import android.content.Context
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import com.example.mvvn.network.RemoteSource
import com.example.weather.database.LocalSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response


class Repository private constructor(var remoteSource: RemoteSource,
                                     var localSource: LocalSource,
                                     var context: Context)
    : RepositoryInterface, RepoOperation {
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
    override suspend fun getCurrentLocationWeather(lat:String,lon:String,lang:String,apiKey:String,unit:String): Flow<Response<WeatherModel>>
    {
        //println("da5alt el repo")
       // val currentWeather= remoteSource.getCurrentWeather(lat,lon,lang,apiKey,unit)
      //  println("bosy da eli wasali delwa2ty:"+currentWeather.current.weather.get(0).id)
       // return currentWeather
        return flow { emit((remoteSource.getCurrentWeather(lat,lon,lang,apiKey,unit))) }
    }

    override suspend fun getStoredCurrentWeatherObjectFromDatabase(): WeatherModel {
 return localSource.getStoredCurrentWeatherObject()
    }



    override suspend fun insertCurrentWeatherObject(weatherObject: WeatherModel) {
    localSource.insertCurrentWeatherObject(weatherObject)
    }

    override suspend fun getStoredFavorites(): Flow<List<FavoriteModel>>? {
    return localSource.getAllStoredFavorites()
    }

//    override val allStoredFavorites: LiveData<List<FavoriteModel>>
//        get() = localSource.allStoredFavorites

    override suspend fun insertToFavorites(favoriteModel: FavoriteModel) {
       localSource.insertToFavorites(favoriteModel)
    }

    override suspend fun deleteFromFavorites(favoriteModel: FavoriteModel) {
    localSource.deleteFromFavorites(favoriteModel)
    }



}