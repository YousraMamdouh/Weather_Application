package com.example.weather.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class FakeRepository(  var favoritesList: MutableList<FavoriteModel> = mutableListOf<FavoriteModel>(),
                       var weatherResponse:WeatherModel=WeatherModel()):RepositoryInterface {
  //  var favoriteListMap=LinkedHashMap<String,FavoriteModel>()


    override suspend fun getCurrentLocationWeather(
        lat: String,
        lon: String,
        lang: String,
        apiKey: String,
        unit: String,
    ): Flow<Response<WeatherModel>> = flow {

    }

    override suspend fun getStoredCurrentWeatherObjectFromDatabase(): WeatherModel {
        TODO("Not yet implemented")
    }

    override suspend fun insertCurrentWeatherObject(weatherObject: WeatherModel) {
        TODO("Not yet implemented")
    }

    override suspend fun getStoredFavorites(): Flow<List<FavoriteModel>>? =flow {
      emit(favoritesList)
    }

    override suspend fun insertToFavorites(favInfo: FavoriteModel) {
      favoritesList.add(favInfo)
    }

    override suspend fun deleteFromFavorites(favInfo: FavoriteModel) {
       favoritesList.remove(favInfo)
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