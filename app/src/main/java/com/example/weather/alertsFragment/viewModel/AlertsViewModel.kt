package com.example.weather.alertsFragment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.model.Alerts
import com.example.weather.model.AlertsModel
import com.example.weather.model.FavoriteModel
import com.example.weather.model.RepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AlertsViewModel(repo: RepositoryInterface) : ViewModel() {

    private val iRepo: RepositoryInterface = repo
    private var _alerts:MutableLiveData<List<AlertsModel>> = MutableLiveData<List<AlertsModel>>()
    val alerts:LiveData<List<AlertsModel>> = _alerts

    init {
        getStoredAlerts()
    }

   fun getStoredAlerts() {
        viewModelScope.launch(Dispatchers.IO) {
            iRepo.getAllStoredAlerts().collect(){
                _alerts.postValue(it)
            }
        }
    }

    fun insetToAlerts(alertsModel:AlertsModel)
{
    viewModelScope.launch(Dispatchers.IO) {
      iRepo.insertToAlerts(alertsModel)
        getStoredAlerts()
    }
}
    fun deleteFromAlerts(id:Int)
    {
        viewModelScope.launch(Dispatchers.IO) {
           iRepo.deleteFromAlerts(id)
            getStoredAlerts()
        }
    }

    fun getAlert(id:Int)
    {
        viewModelScope.launch(Dispatchers.IO) {
            iRepo.getAlert(id)

        }
    }

}