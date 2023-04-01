package com.example.weather.favoritesDetailsFragment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.currentWeather.viewModel.CurrentWeatherViewModelFactory
import com.example.weather.currentWeather.viewModel.FavoritesDetailsViewModelFactory
import com.example.weather.database.ConcreteLocalSource
import com.example.weather.databinding.FragmentFavoriteDetailsBinding
import com.example.weather.databinding.FragmentFavoritesBinding
import com.example.weather.databinding.FragmentHomePageBinding
import com.example.weather.favoritesDetailsFragment.viewmodel.FavoritesDetailsViewModel
import com.example.weather.favoritesfragment.viewmodel.FavoritesViewModel
import com.example.weather.homefragment.view.DaysAdapter
import com.example.weather.homefragment.view.HomePageFragmentArgs
import com.example.weather.homefragment.view.HourlyAdapter
import com.example.weather.homefragment.viewmodel.CurrentWeatherViewModel
import com.example.weather.model.Repository
import com.example.weather.model.WeatherModel
import com.example.weather.network.WeatherClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.ceil


class FavoriteDetailsFragment : Fragment() {
    private lateinit var daysAdapter: DaysAdapter
    private lateinit var hoursAdapter: HourlyAdapter
    val args: FavoriteDetailsFragmentArgs by navArgs()
    private lateinit var viewModel: FavoritesDetailsViewModel
    private lateinit var viewModelFactory: FavoritesDetailsViewModelFactory
    lateinit var binding: FragmentFavoriteDetailsBinding


    lateinit var lat: String
    lateinit var lon: String
    private var lang = "en"
    private val apiKey = "bbcb13e1d448621ffd8e565701972f6d"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        lon =args.longitude
        lat = args.latitude
        daysAdapter = DaysAdapter()
        hoursAdapter = HourlyAdapter()
        binding = FragmentFavoriteDetailsBinding.inflate(inflater, container, false)
        return binding.root
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
        viewModelFactory = FavoritesDetailsViewModelFactory(
            Repository.getInstance(
                WeatherClient.getInstance(),
                ConcreteLocalSource(requireContext()), requireActivity()
            )
        )

        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(FavoritesDetailsViewModel::class.java)

        MainScope().launch(Dispatchers.IO) {
            viewModel.getCurrentWeather(lat, lon, lang, apiKey)
        }

        //Update home details from internet object
        viewModel.onlineWeather.observe(requireActivity()) { current ->
            updateUI(current)
            showCurrentDate()
            selectAppropriateIcon(current)
        }
    }


    private fun selectAppropriateIcon(current: WeatherModel) {

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


    private fun updateUI(current: WeatherModel) {

        binding.weatherDescribtion.text = current.current.weather[0].description
        binding.humidityDesc.text = current.current.humidity.toString()
        //tvVisibility.text=currentWeather.current.visibility.toString()
        binding.cloudsDesc.text = current.current.clouds.toString()
        binding.pressureDesc.text = current.current.pressure.toString()
        binding.windDesc.text = current.current.wind_deg.toString()
        binding.temp.text = ceil(current.current.temp).toInt().toString() + "°C"
        binding.city.text = current.timezone
        binding.humidityIcon.setImageResource(R.drawable.humidty)
        binding.pressureIcon.setImageResource(R.drawable.pressure)
        binding.windIcon.setImageResource(R.drawable.wind)
        binding.cloudsIcon.setImageResource(R.drawable.clouds)
        daysAdapter.submitList(current.daily)
        daysAdapter.notifyDataSetChanged()
        hoursAdapter.submitList(current.hourly)
        hoursAdapter.notifyDataSetChanged()
        val c: Date = Calendar.getInstance().time
        println("Current time => $c")
    }


    private fun showCurrentDate() {
        val c: Date = Calendar.getInstance().time
        println("Current time => $c")

        val df = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
        val formattedDate: String = df.format(c)
        binding.date.text = formattedDate
    }


}