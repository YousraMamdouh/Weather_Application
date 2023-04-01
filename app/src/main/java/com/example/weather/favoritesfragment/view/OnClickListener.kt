package com.example.weather.favoritesfragment.view

import com.example.weather.model.FavoriteModel


interface OnClickListener {
    fun onDeleteClick(favoriteModel: FavoriteModel)
    fun onDisplayClick(favoriteModel: FavoriteModel)
}