package com.example.weather.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.weather.model.FavoriteModel
import com.example.weather.model.WeatherModel
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    //current weather
    @Query("SELECT * FROM weather")
   fun getStoredCurrentWeather():WeatherModel

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCurrentWeatherObject(weatherObject:WeatherModel)

    //favorites
    @Query("SELECT * FROM favorites")
    fun getAllFavorites() : Flow<List<FavoriteModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertToFavorites(favoriteModel: FavoriteModel)

    @Delete
    fun deleteFromFavorites(favoriteModel: FavoriteModel)
}