package com.example.weather.currentWeather.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather.favoritesDetailsFragment.viewmodel.FavoritesDetailsViewModel
import com.example.weather.homefragment.viewmodel.CurrentWeatherViewModel
import com.example.weather.model.Repository
import java.lang.IllegalArgumentException

class FavoritesDetailsViewModelFactory (val repo: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(FavoritesDetailsViewModel::class.java)){
           FavoritesDetailsViewModel(repo) as T
        }
        else{
            throw IllegalArgumentException("This Class not found")
        }
    }
}