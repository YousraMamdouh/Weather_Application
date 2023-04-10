package com.example.weather.database

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.weather.model.AlertsModel
import com.example.weather.model.FavoriteModel
import com.example.weather.model.WeatherModel
import kotlinx.coroutines.flow.Flow


class ConcreteLocalSource(
    context: Context
) : LocalSource {
  ///  override val allStoredFavorites: LiveData<List<FavoriteModel>>

    private val dao: WeatherDao by lazy {
        val db: WeatherDatabase = WeatherDatabase.getInstance(context)
        db.getProductsDAO()
    }
    init {
       // allStoredFavorites = dao.getAllFavorites()
    }

    override suspend fun getStoredCurrentWeatherObject(): WeatherModel {
        return dao.getStoredCurrentWeather()
    }

    override suspend fun insertCurrentWeatherObject(weatherObject: WeatherModel) {
        dao.insertCurrentWeatherObject(weatherObject)
    }

    override suspend fun getAllStoredFavorites(): Flow<List<FavoriteModel>> {
      return dao.getAllFavorites()
    }

    override suspend fun insertToAlerts(alertModel: AlertsModel):Long {
      return  dao.insertAlarm(alertModel)
    }

    override suspend fun deleteFromAlerts(id: Int) {
        dao.deleteAlarm(id)
    }



    override suspend fun getAllStoredAlerts(): Flow<List<AlertsModel>> {
      return dao.getAllAlarms()
    }

    override suspend fun getAlert(id: Int): AlertsModel {
     return  dao.getAlert(id)
    }


    override suspend fun insertToFavorites(favoriteModel: FavoriteModel) {
        dao.insertToFavorites(favoriteModel)
    }

    override suspend fun deleteFromFavorites(favoriteModel: FavoriteModel) {
        dao.deleteFromFavorites(favoriteModel)
    }


}