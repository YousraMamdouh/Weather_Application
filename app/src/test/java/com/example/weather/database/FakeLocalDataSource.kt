package com.example.weather.database

import com.example.mvvn.network.RemoteSource
import com.example.weather.model.AlertsModel
import com.example.weather.model.FavoriteModel
import com.example.weather.model.WeatherModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class FakeLocalDataSource(var favoritesList: MutableList<FavoriteModel> = mutableListOf<FavoriteModel>()):LocalSource {

  //  private var weatherResponse=WeatherModel()
    override suspend fun getStoredCurrentWeatherObject(): WeatherModel {
        TODO("Not yet implemented")
    }

    override suspend fun insertCurrentWeatherObject(weatherObject: WeatherModel) {
        TODO("Not yet implemented")
    }

    override suspend fun insertToFavorites(favoriteModel: FavoriteModel) {
      favoritesList.add(favoriteModel)
    }

    override suspend fun deleteFromFavorites(favoriteModel: FavoriteModel) {
       favoritesList.remove(favoriteModel)
    }

    override suspend fun getAllStoredFavorites(): Flow<List<FavoriteModel>> = flow {
       emit(favoritesList)
    }

    override suspend fun insertToAlerts(alertModel: AlertsModel): Long {
        TODO("Not yet implemented")
    }


    override suspend fun deleteFromAlerts(id: Int) {
        TODO("Not yet implemented")
    }



    override suspend fun getAllStoredAlerts(): Flow<List<AlertsModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAlert(id: Int): AlertsModel {
        TODO("Not yet implemented")
    }


}