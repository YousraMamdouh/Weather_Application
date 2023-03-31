package com.example.weather.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.weather.model.FavoriteModel
import com.example.weather.model.WeatherModel

@Dao
interface WeatherDao {
    //current weather
    @Query("SELECT * FROM weather")
   fun getStoredCurrentWeather():WeatherModel

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCurrentWeatherObject(weatherObject:WeatherModel)

    //favorites
    @Query("SELECT * FROM favorites")
    fun getAllFavorites() : LiveData<List<FavoriteModel>>

    @Insert
    fun insertToFavorites(favoriteModel: FavoriteModel)

    @Delete
    fun deleteFromFavorites(favoriteModel: FavoriteModel)
}