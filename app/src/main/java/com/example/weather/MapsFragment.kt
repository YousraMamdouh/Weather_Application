package com.example.weather

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.weather.databinding.FragmentMapsBinding
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException
import kotlin.collections.ArrayList


class MapsFragment : Fragment() {
    lateinit var binding: FragmentMapsBinding
    lateinit var marker: Marker
    val args: MapsFragmentArgs by navArgs()
    lateinit var myMap: GoogleMap
    var alreadyExecuted = false

    private val callback = OnMapReadyCallback { googleMap ->
        myMap = googleMap
     if(args.latitude.isNullOrEmpty()&&args.longitude.isNullOrEmpty()) {
         val sydney = LatLng(31.2000917, 29.9187383)
         marker = googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
         googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
     }
        else {
         val sydney = LatLng(args.latitude.toDouble(), args.longitude.toDouble())
         marker = googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
         googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
     }


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMapsBinding.inflate(inflater, container, false)
        binding.confirmationButton.setOnClickListener {
            if (alreadyExecuted == false)

                Toast.makeText(context, "Please choose location to confirm", Toast.LENGTH_LONG)
                    .show()

        }
        mapInitialize()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

    }

    fun mapInitialize() {
        val locationRequest = LocationRequest()
        locationRequest.setInterval(5000)
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        locationRequest.setSmallestDisplacement(16f)
        locationRequest.setFastestInterval(3000)

        binding.searchField.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH
                || actionId == EditorInfo.IME_ACTION_DONE
            )
                println("yala raye7")
            goToSearchLocation()


            return@OnEditorActionListener true

            false
        })

    }

    private fun goToSearchLocation() {
        var searchLocation: String = binding.searchField.text.toString()
        var geocoder= Geocoder(requireContext())
        var list: List<Address> = ArrayList()
        try {
            list = geocoder.getFromLocationName(searchLocation, 1)!!

        } catch (e: IOException) {
            e.printStackTrace()
        }
        if (list.size > 0) {
            var address: Address = list.get(0)
            var country:String=address.countryName.toString()
            var location = address.adminArea
            var latitude: Double = address.latitude
            var longitude: Double = address.longitude

            if (marker != null) {
                marker.remove()
            }
            var markerOptions: MarkerOptions
            markerOptions = MarkerOptions()
            markerOptions.title(location)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            markerOptions.position(LatLng(latitude, longitude))
            myMap.addMarker(markerOptions)
            myMap.moveCamera(CameraUpdateFactory.newLatLng(markerOptions.position))
       // moveMarkerToRequiredLocation(location,latitude,longitude)


            println("el location $country el lon $longitude el lat $latitude")
            alreadyExecuted = true

            binding.confirmationButton.setOnClickListener {
                if(args.latitude.isNullOrEmpty()&&args.longitude.isNullOrEmpty())
                {


                    navigateToFavorites(longitude.toString(),latitude.toString())
                }else{
                    navigateToHome(longitude.toString(), latitude.toString())
                }


            }
        }

    }

    private fun navigateToHome(long: String, lat: String) {
        val action = MapsFragmentDirections.actionMapsFragmentToHomePageFragment2(long, lat)
        Navigation.findNavController(requireActivity(), R.id.fragmentView).navigate(action)

    }
    private fun navigateToFavorites(long: String, lat: String) {
        val action = MapsFragmentDirections.actionMapsFragmentToFavoritesFragment2(long,lat)
        Navigation.findNavController(requireActivity(), R.id.fragmentView).navigate(action)

    }

    fun moveMarkerToRequiredLocation(location:String,latitude:Double,longitude:Double)
    {

    }
}