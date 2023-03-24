package com.example.weather.currentWeather.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather.homefragment.viewmodel.CurrentWeatherViewModel
import com.example.weather.model.Repository
import java.lang.IllegalArgumentException

class CurrentWeatherViewModelFactory (val repo: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CurrentWeatherViewModel::class.java)){
            CurrentWeatherViewModel(repo) as T
        }
        else{
            throw IllegalArgumentException("This Class not found")
        }
    }
}