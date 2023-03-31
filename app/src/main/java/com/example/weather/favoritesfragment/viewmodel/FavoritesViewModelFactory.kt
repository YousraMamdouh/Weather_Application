package com.example.weather.favoritesfragment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather.homefragment.viewmodel.CurrentWeatherViewModel
import com.example.weather.model.Repository
import java.lang.IllegalArgumentException

class FavoritesViewModelFactory (val repo: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)){
           FavoritesViewModel(repo) as T
        }
        else{
            throw IllegalArgumentException("This Class not found")
        }
    }
}