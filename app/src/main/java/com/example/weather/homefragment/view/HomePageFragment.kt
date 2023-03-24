package com.example.weather.homefragment.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mvvn.network.RemoteSource
import com.example.weather.currentWeather.viewModel.CurrentWeatherViewModelFactory
import com.example.weather.database.ConcreteLocalSource
import com.example.weather.databinding.FragmentHomePageBinding
import com.example.weather.homefragment.viewmodel.CurrentWeatherViewModel
import com.example.weather.model.Repository
import com.example.weather.model.WeatherModel
import com.example.weather.network.WeatherClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class HomePageFragment : Fragment() {


    lateinit var binding: FragmentHomePageBinding
    private lateinit var currentViewModel: CurrentWeatherViewModel
    private lateinit var currentViewModelFactory: CurrentWeatherViewModelFactory
    private lateinit var weatherObj: WeatherModel
    private var lat = "33.44"
    private var lon = "-94.04"
    private var lang = "en"
    private val apiKey = "4a059725f93489b95183bbcb8c6829b9"


    companion object {
        private const val PERMISSION_ID = 100

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        // currentViewModel = ViewModelProvider(this, currentViewModelFactory).get(CurrentWeatherViewModel::class.java)

        println("ana fl home fragment, raye7 agebo w el lon:" + lon)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("raye7 agebo w el lon:" + lon)
        currentViewModelFactory = CurrentWeatherViewModelFactory(
            Repository.getInstance(
                WeatherClient.getInstance(),
                ConcreteLocalSource(requireContext()), requireActivity()
            )
        )

        currentViewModel = ViewModelProvider(
            this,
            currentViewModelFactory
        ).get(CurrentWeatherViewModel::class.java)

        MainScope().launch(Dispatchers.IO) {
            weatherObj = currentViewModel.getCurrentWeather(lat, lon, lang, apiKey)
            updateUi()

        }
//        MainScope().launch(Dispatchers.Main)
//        {
//            updateUi()
//        }


    }

    fun updateUi() {
        binding.temp.text = weatherObj.timezone
        binding.weatherDescribtion.text = weatherObj.current.feels_like.toString()

    }

}


