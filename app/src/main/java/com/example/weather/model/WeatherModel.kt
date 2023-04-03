package com.example.weather.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.weather.database.Converters

@Entity(tableName = "weather")
@TypeConverters(Converters::class)
data class WeatherModel(

    val current: Current,
    val daily: List<Daily>,
    val hourly: List<Hourly>,
    @PrimaryKey
    val lat: Double,
    val lon: Double,
//    val minutely: List<Minutely>,
    val timezone: String,
    val timezone_offset: Int,
//    var alerts:List<Alerts>
)