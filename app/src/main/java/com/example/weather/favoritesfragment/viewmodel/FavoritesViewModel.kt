package com.example.weather.favoritesfragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.model.FavoriteModel
import com.example.weather.model.RepositoryInterface
import com.example.weather.model.WeatherModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesViewModel(repo: RepositoryInterface) : ViewModel() {
   // private lateinit var weather: WeatherModel
    private val iRepo: RepositoryInterface = repo
    private val currentLocationWeather = MutableLiveData<WeatherModel>()

    private var _favorites:MutableLiveData<List<FavoriteModel>> = MutableLiveData<List<FavoriteModel>>()
    val favorites:LiveData<List<FavoriteModel>> = _favorites

    val onlineWeather: LiveData<WeatherModel> = currentLocationWeather


    init {
        getLocalFavorites()
    }

    private fun getLocalFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            _favorites.postValue(iRepo.getStoredFavorites())
        }
    }

    fun insetToFavorites(favoriteModel: FavoriteModel)
{
    viewModelScope.launch(Dispatchers.IO) {
      iRepo.insertToFavorites(favoriteModel)
    }
}
    fun deleteFromFavorites(favoriteModel: FavoriteModel)
    {
        viewModelScope.launch(Dispatchers.IO) {
           iRepo.deleteFromFavorites(favoriteModel)
        }
    }
//    fun getCurrentWeather(lat: String, lon: String, lang: String, apiKey: String) {
//
//        viewModelScope.launch {
//            println("raye7 agebo w el lon:" + lon)
//            val weather = iRepo.getCurrentLocationWeather(lat, lon, lang, apiKey)
//            withContext(Dispatchers.Main) {
//                currentLocationWeather.postValue(weather)
//            }
//        }
//
//    }

//    fun getAllFavorites():LiveData<List<FavoriteModel>>
//    {
//        return iRepo.allStoredFavorites
//    }
}