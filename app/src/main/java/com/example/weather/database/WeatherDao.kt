package com.example.weather.database

import androidx.room.*
import com.example.weather.model.WeatherModel

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather")
    suspend fun getStoredCurrentWeather():WeatherModel

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCurrentWeatherObject(weatherObject:WeatherModel)
}