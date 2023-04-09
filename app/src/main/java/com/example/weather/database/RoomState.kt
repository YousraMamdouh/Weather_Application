package com.example.weather.database


import com.example.weather.model.FavoriteModel

sealed class RoomState{
    class Success (val data: List<FavoriteModel>): RoomState()
    class Failure(val msg:Throwable): RoomState()
    object Loading: RoomState()
}
