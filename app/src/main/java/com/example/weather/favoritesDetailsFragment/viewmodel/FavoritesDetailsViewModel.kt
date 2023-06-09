package com.example.weather.favoritesDetailsFragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.model.RepositoryInterface
import com.example.weather.model.Weather
import com.example.weather.model.WeatherModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesDetailsViewModel(repo: RepositoryInterface) : ViewModel() {
   // private lateinit var weather: WeatherModel
    private val iRepo: RepositoryInterface = repo
    private val currentLocationWeather = MutableLiveData<WeatherModel>()
  //  private val currentLocationWeatherFromDatabase=MutableLiveData<WeatherModel>()

    //Expose returned online Data
    val onlineWeather: LiveData<WeatherModel> = currentLocationWeather
  //  val offlineWeather: LiveData<WeatherModel> = currentLocationWeatherFromDatabase
    fun getCurrentWeather(lat: String, lon: String, lang: String, apiKey: String,unit:String) {

      viewModelScope.launch {
          println("raye7 agebo w el lon:" + lon)
          val weather = iRepo.getCurrentLocationWeather(lat, lon, lang, apiKey,unit)
          withContext(Dispatchers.Main) {
              // currentLocationWeather.postValue(weather)
              weather.collect{
                  currentLocationWeather.postValue(it)

              }
              //   currentLocationWeather.value=ApiState.Failure(it)

          }
      }

    }

//    fun getCurrentWeatherObjectFromDatabase()
//    {
//        viewModelScope.launch {
//
//            withContext(Dispatchers.IO) {
//                val weather = iRepo.getStoredCurrentWeatherObjectFromDatabase()
//                currentLocationWeatherFromDatabase.postValue(weather)
//            }
//        }
//
//
//    }

//    fun insertCurrentWeather(weatherObj: WeatherModel) {
//
//        viewModelScope.launch(Dispatchers.IO) {
//          iRepo.insertCurrentWeatherObject(weatherObj)
//        }
//    }




}