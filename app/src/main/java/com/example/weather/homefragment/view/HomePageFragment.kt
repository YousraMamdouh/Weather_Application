package com.example.weather.homefragment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.weather.currentWeather.viewModel.CurrentWeatherViewModelFactory
import com.example.weather.database.ConcreteLocalSource
import com.example.weather.databinding.FragmentHomePageBinding
import com.example.weather.homefragment.viewmodel.CurrentWeatherViewModel
import com.example.weather.model.Repository
import com.example.weather.network.WeatherClient


class HomePageFragment : Fragment() {


lateinit var binding: FragmentHomePageBinding
    private lateinit var currentViewModel: CurrentWeatherViewModel
    private lateinit var currentViewModelFactory: CurrentWeatherViewModelFactory
    private var lat = "33.44"
    private var lon = "-94.04"
    private var lang = "en"
    private val apiKey= "524d6128b39af7be6274529023557609"


    companion object {
        private const val PERMISSION_ID = 100

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomePageBinding.inflate(inflater,container,false)
       // currentViewModel = ViewModelProvider(this, currentViewModelFactory).get(CurrentWeatherViewModel::class.java)

        println("ana fl home fragment, raye7 agebo w el lon:"+lon)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("raye7 agebo w el lon:"+lon)
        currentViewModelFactory = CurrentWeatherViewModelFactory(
            Repository.getInstance(
                WeatherClient.getInstance(),
                ConcreteLocalSource(requireContext()),requireActivity()))

        currentViewModel = ViewModelProvider(this, currentViewModelFactory).get(CurrentWeatherViewModel::class.java)
        currentViewModel.getCurrentWeather(lat,lon,lang,apiKey)

    }


      //  currentViewModel = ViewModelProvider(this, currentViewModelFactory).get(CurrentWeatherViewModel::class.java)

    }

      //   currentViewModel = ViewModelProvider(this, currentViewModelFactory).get(CurrentWeatherViewModel::class.java)

        //  currentViewModel.getCurrentWeather(lat,lon,lang,apiKey)
     // binding.textView3.text="Yousra"

