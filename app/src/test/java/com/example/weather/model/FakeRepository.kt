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
}