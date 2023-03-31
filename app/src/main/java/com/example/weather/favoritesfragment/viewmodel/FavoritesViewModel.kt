package com.example.weather.favoritesfragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.model.FavoriteModel
import com.example.weather.model.RepositoryInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel(repo: RepositoryInterface) : ViewModel() {
   // private lateinit var weather: WeatherModel
    private val iRepo: RepositoryInterface = repo
    private var _favorites:MutableLiveData<List<FavoriteModel>> = MutableLiveData<List<FavoriteModel>>()
    val favorites:LiveData<List<FavoriteModel>> = _favorites

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

//    fun getAllFavorites():LiveData<List<FavoriteModel>>
//    {
//        return iRepo.allStoredFavorites
//    }
}