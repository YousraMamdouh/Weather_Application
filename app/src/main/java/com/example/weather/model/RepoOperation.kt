package com.example.weather.model

import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RepoOperation {
    suspend fun getCurrentLocationWeather(
        lat: String,
        lon: String,
        lang: String,
        apiKey: String,
        unit: String,
    ): Flow<Response<WeatherModel>>

    suspend fun getStoredCurrentWeatherObjectFromDatabase(): WeatherModel

    suspend fun insertCurrentWeatherObject(weatherObject: WeatherModel)

    suspend fun getStoredFavorites(): Flow<List<FavoriteModel>>?

    suspend fun insertToFavorites(favoriteModel: FavoriteModel)

    suspend fun deleteFromFavorites(favoriteModel: FavoriteModel)
}