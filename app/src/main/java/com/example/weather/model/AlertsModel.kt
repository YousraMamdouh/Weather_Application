package com.example.weather.model


import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "alerts")
 data class AlertsModel(
     @PrimaryKey(autoGenerate = true)
     val id: Int,
     val startTime : Long,
     val startDate : Long,
     val endDate : Long,
     val endTime : Long
 )

