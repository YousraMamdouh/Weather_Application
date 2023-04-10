package com.example.weather.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weather.model.AlertsModel
import com.example.weather.model.FavoriteModel
import com.example.weather.model.WeatherModel

@Database(entities = [WeatherModel::class,FavoriteModel::class,AlertsModel::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun getProductsDAO(): WeatherDao

    companion object {
        @Volatile
        private var INSTANCE: WeatherDatabase? = null
        fun getInstance(context: Context): WeatherDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, WeatherDatabase::class.java, "products")
                .build()
                INSTANCE=instance
                instance
            }
        }
    }
}