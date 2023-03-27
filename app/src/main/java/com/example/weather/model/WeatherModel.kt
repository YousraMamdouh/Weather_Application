package com.example.weather.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")

data class WeatherModel(

    val current: Current,
    val daily: List<Daily>,
    val hourly: List<Hourly>,
    @PrimaryKey
    val lat: Double,
    val lon: Double,
    val minutely: List<Minutely>,
    val timezone: String,
    val timezone_offset: Int,
    var alerts:ArrayList<Alerts>
)