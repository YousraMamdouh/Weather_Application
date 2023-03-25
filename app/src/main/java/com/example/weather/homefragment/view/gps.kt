package com.example.weather.homefragment.view
//
//package com.example.weather.homefragment.view
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.content.Intent
//import android.content.SharedPreferences
//import android.content.pm.PackageManager
//import android.location.Address
//import android.location.Geocoder
//import android.location.Location
//import android.location.LocationManager
//import android.os.Bundle
//import android.os.Looper
//import android.provider.Settings
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.appcompat.app.AlertDialog
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat.getSystemService
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.ViewModelProvider
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.weather.R
//import com.example.weather.currentWeather.viewModel.CurrentWeatherViewModelFactory
//import com.example.weather.database.ConcreteLocalSource
//import com.example.weather.databinding.FragmentHomePageBinding
//import com.example.weather.homefragment.viewmodel.CurrentWeatherViewModel
//import com.example.weather.model.Repository
//import com.example.weather.network.WeatherClient
//import com.google.android.gms.location.*
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.MainScope
//import kotlinx.coroutines.launch
//import java.text.SimpleDateFormat
//import java.util.*
//
//const val PERISSION_ID = 44
//
//class HomePageFragment : Fragment() {
//    lateinit var mFusedLocationClient: FusedLocationProviderClient
//    private lateinit var sharedPreferences: SharedPreferences
//    private lateinit var editor: SharedPreferences.Editor
//
//
//    lateinit var binding: FragmentHomePageBinding
//    private lateinit var currentViewModel: CurrentWeatherViewModel
//    private lateinit var currentViewModelFactory: CurrentWeatherViewModelFactory
//    private lateinit var daysAdapter: DaysAdapter
//    private lateinit var hoursAdapter: HourlyAdapter
//
//    // private lateinit var weatherObj: WeatherModel
//    private var lat = ""
//    private var lon = ""
//    private var lang = "en"
//    private val apiKey = "4a059725f93489b95183bbcb8c6829b9"
////    private val apiKey = "524d6128b39af7be6274529023557609"
//
//
//    //    companion object {
////        private const val PERMISSION_ID = 100
////
////    }
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?,
//    ): View? {
//        daysAdapter = DaysAdapter()
//        hoursAdapter = HourlyAdapter()
//        // Inflate the layout for this fragment
//        binding = FragmentHomePageBinding.inflate(inflater, container, false)
//        // currentViewModel = ViewModelProvider(this, currentViewModelFactory).get(CurrentWeatherViewModel::class.java)
//        //mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
//        //////
//
//        //////
//        // getMyCurrentLocation()
//        println("ana fl home fragment, raye7 agebo w el lon:" + lon)
//        return binding.root
//    }
//
////    private fun showDialogOpenLocationSettings() {
////        val builder = AlertDialog.Builder(requireContext())
////        //set title for alert dialog
////        builder.setTitle("R.string.go_to_location_settings_dialog_title")
////        //set message for alert dialog
////        builder.setMessage("R.string.go_to_location_settings_dialog_msg")
////        builder.setIcon(android.R.drawable.ic_dialog_alert)
////
////        //performing positive action
////        builder.setPositiveButton("Allow") { dialogInterface, which ->
////            Toast.makeText(requireContext(), "clicked Allow", Toast.LENGTH_LONG).show()
////            goToLocationSettings()
////        }
////        //performing cancel action
////        builder.setNegativeButton("Cancel") { dialogInterface, which ->
////            Toast.makeText(requireContext(), "clicked cancel\n operation cancel", Toast.LENGTH_LONG)
////                .show()
////            requireActivity().finish()
////        }
////
////        // Create the AlertDialog
////        val alertDialog: AlertDialog = builder.create()
////        // Set other dialog properties
////        alertDialog.setCancelable(false)
////        alertDialog.show()
////    }
////
////    private fun goToLocationSettings() {
////        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
////        startActivity(intent)
////    }
////
////
////    @SuppressLint("MissingPermission")
////    private fun getMyCurrentLocation() {
////        if (checkPermission()) {
////            if (isLocationEnabled()) {
////                requestNewLocationData()
////                showDialogOpenLocationSettings()
////            } else {
////                Toast.makeText(requireContext(), "Turn on location", Toast.LENGTH_LONG).show()
////                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
////                startActivity(intent)
////            }
////        } else {
////            requestPermissions()
////        }
////    }
////
////    private fun isLocationEnabled(): Boolean {
////        val locationManager: LocationManager =
////            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
////        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
////            LocationManager.NETWORK_PROVIDER
////        )
////    }
////
////    private fun checkPermission(): Boolean {
////        return ActivityCompat.checkSelfPermission(
////            requireContext(),
////            android.Manifest.permission.ACCESS_COARSE_LOCATION
////        ) == PackageManager.PERMISSION_GRANTED ||
////                ActivityCompat.checkSelfPermission(
////                    requireContext(),
////                    android.Manifest.permission.ACCESS_FINE_LOCATION
////                ) == PackageManager.PERMISSION_GRANTED
////    }
////
////    private fun requestPermissions() {
////        ActivityCompat.requestPermissions(
////            requireActivity(),
////            arrayOf(
////                android.Manifest.permission.ACCESS_COARSE_LOCATION,
////                android.Manifest.permission.ACCESS_FINE_LOCATION
////
////            ),
////            PERISSION_ID
////
////        )
////    }
////
////    private val mLocationCallback: LocationCallback = object : LocationCallback() {
////        override fun onLocationResult(locationResult: LocationResult) {
////            val mLastLocation: Location = locationResult.lastLocation
////            // latTextView.text=mLastLocation.latitude.toString()
////            //lonTextView.text=mLastLocation.longitude.toString()
////            lon = mLastLocation.longitude.toString()
////            lang = mLastLocation.latitude.toString()
////            val geocoder = Geocoder(requireContext(), Locale.getDefault())
////            val addresses: List<Address>? =
////                geocoder.getFromLocation(mLastLocation.latitude, mLastLocation.longitude, 1)
//////            println("Addresses size" + addresses!!.size)
//////            countery=addresses?.get(0)?.countryName.toString()
//////            informationTextView.text=countery
////
////        }
////    }
////
////    @SuppressLint("MissingPermission")
////    private fun requestNewLocationData() {
////        val myLocationRequest = LocationRequest()
////        myLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
////        myLocationRequest.setInterval(0)
////        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
////        mFusedLocationClient.requestLocationUpdates(
////            myLocationRequest, mLocationCallback,
////            Looper.myLooper()
////        )
////    }
//
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.daysRecyclerView.apply {
//            this.adapter = daysAdapter
//            layoutManager = LinearLayoutManager(context).apply {
//                orientation = RecyclerView.VERTICAL
//            }
//        }
//        binding.hoursRecyclerView.apply {
//            this.adapter = hoursAdapter
//            layoutManager = LinearLayoutManager(context).apply {
//                orientation = RecyclerView.HORIZONTAL
//            }
//        }
//        println("raye7 agebo w el lon:" + lon)
//        currentViewModelFactory = CurrentWeatherViewModelFactory(
//            Repository.getInstance(
//                WeatherClient.getInstance(),
//                ConcreteLocalSource(requireContext()), requireActivity()
//            )
//        )
//
//        currentViewModel = ViewModelProvider(
//            this,
//            currentViewModelFactory
//        ).get(CurrentWeatherViewModel::class.java)
//
//        MainScope().launch(Dispatchers.IO) {
//            currentViewModel.getCurrentWeather(lat, lon, lang, apiKey)
////            updateUi()
//
//        }
//        updateUi()
//
//    }
//
//    fun updateUi() {
//        currentViewModel.onlineWeather.observe(requireActivity()) { current ->
////            daysAdapter.submitList(current.current as ArrayList<Current>)
//
//            binding.weatherDescribtion.text = current.current.weather[0].description
//            binding.humidityDesc.text = current.current.humidity.toString()
//            //tvVisibility.text=currentWeather.current.visibility.toString()
//            binding.cloudsDesc.text = current.current.clouds.toString()
//            binding.pressureDesc.text = current.current.pressure.toString()
//            binding.windDesc.text = current.current.wind_deg.toString()
//            binding.temp.text = Math.ceil(current.current.temp).toInt().toString() + "°C"
//            binding.city.text = current.timezone
//            binding.humidityIcon.setImageResource(R.drawable.humidty)
//            binding.pressureIcon.setImageResource(R.drawable.pressure)
//            binding.windIcon.setImageResource(R.drawable.wind)
//            binding.cloudsIcon.setImageResource(R.drawable.clouds)
//            daysAdapter.submitList(current.daily)
//            daysAdapter.notifyDataSetChanged()
//            hoursAdapter.submitList(current.hourly)
//            hoursAdapter.notifyDataSetChanged()
//            val c: Date = Calendar.getInstance().getTime()
//            println("Current time => $c")
//
//            val df = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
//            val formattedDate: String = df.format(c)
//            binding.date.text = formattedDate
//
////            Glide.with(requireContext())
////                .load("https://openweathermap.org/img/w/${current.current.weather[0].icon}.png")
////                .into(binding.weatherIcon)
//
//            when (current.current.weather[0].main) {
//                "Clouds" -> binding.weatherIcon.setImageResource(R.drawable.current_cloudy)
//                "Clear" -> binding.weatherIcon.setImageResource(R.drawable.current_sun)
//                "Thunderstorm" -> binding.weatherIcon.setImageResource(R.drawable.cloudy_storm)
//                "Drizzle" -> binding.weatherIcon.setImageResource(R.drawable.current_rain)
//                "Rain" -> binding.weatherIcon.setImageResource(R.drawable.current_rain)
//                "Snow" -> binding.weatherIcon.setImageResource(R.drawable.current_snow)
//                "Mist" -> binding.weatherIcon.setImageResource(R.drawable.current_fog)
//                "Smoke" -> binding.weatherIcon.setImageResource(R.drawable.current_fog)
//                "Haze" -> binding.weatherIcon.setImageResource(R.drawable.current_fog)
//                "Dust" -> binding.weatherIcon.setImageResource(R.drawable.current_fog)
//                "Fog" -> binding.weatherIcon.setImageResource(R.drawable.current_fog)
//                "Sand" -> binding.weatherIcon.setImageResource(R.drawable.current_fog)
//                "Ash" -> binding.weatherIcon.setImageResource(R.drawable.current_fog)
//                "Squall" -> binding.weatherIcon.setImageResource(R.drawable.current_squall)
//                "Tornado" -> binding.weatherIcon.setImageResource(R.drawable.ic_tornado)
//            }
//
//        }
//
//    }
//
//}


