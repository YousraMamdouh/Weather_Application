package com.example.weather.homefragment.view

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.currentWeather.viewModel.CurrentWeatherViewModelFactory
import com.example.weather.database.ConcreteLocalSource
import com.example.weather.databinding.CustomDialogueBinding
import com.example.weather.databinding.FragmentHomePageBinding
import com.example.weather.homefragment.viewmodel.CurrentWeatherViewModel
import com.example.weather.model.Repository
import com.example.weather.network.WeatherClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class HomePageFragment : Fragment() {


    lateinit var binding: FragmentHomePageBinding

    private lateinit var currentViewModel: CurrentWeatherViewModel
    private lateinit var currentViewModelFactory: CurrentWeatherViewModelFactory
    private lateinit var daysAdapter: DaysAdapter
    private lateinit var hoursAdapter: HourlyAdapter
    private var isGpsChecked=false;
   // private lateinit var weatherObj: WeatherModel

    lateinit var dialog: Dialog
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
        daysAdapter= DaysAdapter()
        hoursAdapter= HourlyAdapter()
        // Inflate the layout for this fragment
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        // currentViewModel = ViewModelProvider(this, currentViewModelFactory).get(CurrentWeatherViewModel::class.java)
       // showDialogOpenLocationSettings()

        println("ana fl home fragment, raye7 agebo w el lon:" + lon)
        return binding.root
    }



        private fun goToLocationSettings() {
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        startActivity(intent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.daysRecyclerView.apply {
            this.adapter = daysAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = RecyclerView.VERTICAL
            }
        }
        binding.hoursRecyclerView.apply {
            this.adapter = hoursAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = RecyclerView.HORIZONTAL
            }
        }
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
          currentViewModel.getCurrentWeather(lat, lon, lang, apiKey)
//            updateUi()

            }
        updateUi()

    }

    fun updateUi() {
        currentViewModel.onlineWeather.observe(requireActivity()){
                current->
//            daysAdapter.submitList(current.current as ArrayList<Current>)

            binding.weatherDescribtion.text=current.current.weather[0].description
            binding.humidityDesc.text=current.current.humidity.toString()
            //tvVisibility.text=currentWeather.current.visibility.toString()
            binding.cloudsDesc.text=current.current.clouds.toString()
            binding.pressureDesc.text=current.current.pressure.toString()
            binding.windDesc.text=current.current.wind_deg.toString()
            binding.temp.text=Math.ceil(current.current.temp).toInt().toString()+"°C"
            binding.city.text=current.timezone
            binding.humidityIcon.setImageResource(R.drawable.humidty)
            binding.pressureIcon.setImageResource(R.drawable.pressure)
            binding.windIcon.setImageResource(R.drawable.wind)
            binding.cloudsIcon.setImageResource(R.drawable.clouds)
            daysAdapter.submitList(current.daily)
            daysAdapter.notifyDataSetChanged()
            hoursAdapter.submitList(current.hourly)
            hoursAdapter.notifyDataSetChanged()
            val c: Date = Calendar.getInstance().getTime()
            println("Current time => $c")

            val df = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
            val formattedDate: String = df.format(c)
            binding.date.text=formattedDate

//            Glide.with(requireContext())
//                .load("https://openweathermap.org/img/w/${current.current.weather[0].icon}.png")
//                .into(binding.weatherIcon)

            when (current.current.weather[0].main) {
                "Clouds" -> binding.weatherIcon.setImageResource(R.drawable.current_cloudy)
                "Clear" -> binding.weatherIcon.setImageResource(R.drawable.current_sun)
                "Thunderstorm" -> binding.weatherIcon.setImageResource(R.drawable.cloudy_storm)
                "Drizzle" -> binding.weatherIcon.setImageResource(R.drawable.current_rain)
                "Rain" -> binding.weatherIcon.setImageResource(R.drawable.current_rain)
                "Snow" -> binding.weatherIcon.setImageResource(R.drawable.current_snow)
                "Mist" -> binding.weatherIcon.setImageResource(R.drawable.current_fog)
                "Smoke" -> binding.weatherIcon.setImageResource(R.drawable.current_fog)
                "Haze" -> binding.weatherIcon.setImageResource(R.drawable.current_fog)
                "Dust" -> binding.weatherIcon.setImageResource(R.drawable.current_fog)
                "Fog" -> binding.weatherIcon.setImageResource(R.drawable.current_fog)
                "Sand" -> binding.weatherIcon.setImageResource(R.drawable.current_fog)
                "Ash" -> binding.weatherIcon.setImageResource(R.drawable.current_fog)
                "Squall" -> binding.weatherIcon.setImageResource(R.drawable.current_squall)
                "Tornado" -> binding.weatherIcon.setImageResource(R.drawable.ic_tornado)
            }

        }

    }



}


