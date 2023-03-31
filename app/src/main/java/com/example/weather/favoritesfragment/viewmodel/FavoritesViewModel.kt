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


fun insetToFavorites(favoriteModel: FavoriteModel)
{
    CoroutineScope(Dispatchers.IO).launch {
      iRepo.insertToFavorites(favoriteModel)
    }
}
    fun deleteFromFavorites(favoriteModel: FavoriteModel)
    {
        viewModelScope.launch(Dispatchers.IO) {
           iRepo.deleteFromFavorites(favoriteModel)
        }
    }

    fun getAllFavorites():LiveData<List<FavoriteModel>>
    {
        return iRepo.allStoredFavorites
    }
}