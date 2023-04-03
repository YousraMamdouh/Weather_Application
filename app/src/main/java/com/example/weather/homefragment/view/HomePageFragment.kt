package com.example.weather.homefragment.view


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
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
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.ceil


class HomePageFragment : Fragment() {

    val args: HomePageFragmentArgs by navArgs()
    lateinit var binding: FragmentHomePageBinding

    private lateinit var currentViewModel: CurrentWeatherViewModel
    private lateinit var currentViewModelFactory: CurrentWeatherViewModelFactory
    private lateinit var daysAdapter: DaysAdapter
    private lateinit var hoursAdapter: HourlyAdapter

    lateinit var lat: String
    lateinit var lon: String
    private var lang = "en"
    private val apiKey = "bbcb13e1d448621ffd8e565701972f6d"
    private val unit= "metric"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        lon = args.lon
        lat = args.lan
        daysAdapter = DaysAdapter()
        hoursAdapter = HourlyAdapter()
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        return binding.root
    }


//    private fun goToLocationSettings() {
//        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
//        startActivity(intent)
//    }

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

        //network check
        val connectionManager: ConnectivityManager =
            activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectionManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        if (isConnected) {
            println("fe net ya basha")
            MainScope().launch(Dispatchers.IO) {
                currentViewModel.getCurrentWeather(lat, lon, lang, apiKey,unit)
            }

            //Update home details from internet object
            currentViewModel.onlineWeather.observe(requireActivity()) { current ->
                insertCurrentInDatabase(current)
                updateUI(current)
                showCurrentDate()
                selectAppropriateIcon(current)
            }


        } else {
            MainScope().launch(Dispatchers.IO) {
                currentViewModel.getCurrentWeatherObjectFromDatabase()
            }

            currentViewModel.offlineWeather.observe(requireActivity())
            {

                updateUI(it)
                showCurrentDate()
                selectAppropriateIcon(it)
                println("eli m3aya ${it.current.weather}")
            }
            println("mafeesh net ya amar")


        }
    }


    private fun insertCurrentInDatabase(current: WeatherModel) {
        lifecycleScope.launch {
            withContext(Dispatchers.Main) {
                currentViewModel.insertCurrentWeather(current)
            }
        }
    }


    private fun selectAppropriateIcon(current: WeatherModel) {

        when (current.current.weather[0].main) {
            "Clouds" -> binding.weatherIcon.setImageResource(R.drawable._current_cloudy)
            "Clear" -> binding.weatherIcon.setImageResource(R.drawable._sun)
            "Thunderstorm" -> binding.weatherIcon.setImageResource(R.drawable._current_cloudy_storm)
            "Drizzle" -> binding.weatherIcon.setImageResource(R.drawable._current_rain)
            "Rain" -> binding.weatherIcon.setImageResource(R.drawable._current_rain)
            "Snow" -> binding.weatherIcon.setImageResource(R.drawable._current_snow)
            "Mist" -> binding.weatherIcon.setImageResource(R.drawable._current_fog)
            "Smoke" -> binding.weatherIcon.setImageResource(R.drawable._current_fog)
            "Haze" -> binding.weatherIcon.setImageResource(R.drawable._current_fog)
            "Dust" -> binding.weatherIcon.setImageResource(R.drawable._current_fog)
            "Fog" -> binding.weatherIcon.setImageResource(R.drawable._current_fog)
            "Sand" -> binding.weatherIcon.setImageResource(R.drawable._current_fog)
            "Ash" -> binding.weatherIcon.setImageResource(R.drawable._current_fog)
            "Squall" -> binding.weatherIcon.setImageResource(R.drawable.current_squall)
            "Tornado" -> binding.weatherIcon.setImageResource(R.drawable._tornado)
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
        binding.humidityIcon.setImageResource(R.drawable.humidity_icon)
        binding.pressureIcon.setImageResource(R.drawable.pressure_icon)
        binding.windIcon.setImageResource(R.drawable.wind)
        binding.cloudsIcon.setImageResource(R.drawable.cloud_icon)
        daysAdapter.submitList(current.daily)
        daysAdapter.notifyDataSetChanged()
        hoursAdapter.submitList(current.hourly)
        hoursAdapter.notifyDataSetChanged()
        val c: Date = Calendar.getInstance().time
        println("Current time => $c")
    }

private fun setTempreature(current:WeatherModel)
{

}
    private fun showCurrentDate() {
        val c: Date = Calendar.getInstance().time
        println("Current time => $c")

        val df = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
        val formattedDate: String = df.format(c)
        binding.date.text = formattedDate
    }


}


