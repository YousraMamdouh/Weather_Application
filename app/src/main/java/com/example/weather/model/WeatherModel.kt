package com.example.weather.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.weather.database.Converters

@Entity(tableName = "weather")
@TypeConverters(Converters::class)
data class WeatherModel(

    val current: Current?=null,
    val daily: List<Daily>?=null,
    val hourly: List<Hourly>?=null,
    @PrimaryKey
    val lat: Double=2.2,
    val lon: Double?=null,
//    val minutely: List<Minutely>,
    val timezone: String?=null,
    val timezone_offset: Int?=null,
//    var alerts:List<Alerts>
)