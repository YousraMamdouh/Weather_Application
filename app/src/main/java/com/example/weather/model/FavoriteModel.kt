package com.example.weather.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "favorites")
data class FavoriteModel(
    @PrimaryKey
    val locality:String,
    val lat:Double,
    val lon: Double
): Serializable