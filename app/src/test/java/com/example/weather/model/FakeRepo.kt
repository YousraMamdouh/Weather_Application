//package com.example.weather.model
//
//import kotlinx.coroutines.flow.Flow
//import org.junit.Assert.*
//
//class FakeRepo:RepoOperation {
//    var favoriteListMap:LinkedHashMap<String,FavoriteModel> =LinkedHashMap()
//    override suspend fun getCurrentLocationWeather(
//        lat: String,
//        lon: String,
//        lang: String,
//        apiKey: String,
//        unit: String,
//    ): Flow<WeatherModel> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun getStoredCurrentWeatherObjectFromDatabase(): WeatherModel {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun insertCurrentWeatherObject(weatherObject: WeatherModel) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun getStoredFavorites(): Flow<List<FavoriteModel>>? {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun insertToFavorites(favoriteModel: FavoriteModel) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun deleteFromFavorites(favoriteModel: FavoriteModel) {
//        TODO("Not yet implemented")
//    }
//}