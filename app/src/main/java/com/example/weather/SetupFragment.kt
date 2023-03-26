package com.example.weather

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.text.Layout.Directions
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import com.example.weather.databinding.CustomDialogueBinding
import com.google.android.gms.location.*
import com.google.android.material.navigation.NavigationView

const val PERISSION_ID = 44

class SetupFragment : Fragment() {
    lateinit var navigationView: NavigationView
    lateinit var dialogueBinding: CustomDialogueBinding
    lateinit var myView:View
    lateinit var mFusedLocationClient: FusedLocationProviderClient
    lateinit var dialog: Dialog
    private var isGpsChecked = false;
    var lan=""
    var lon=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog = Dialog(requireContext())


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        println("gowa el on create ")

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
       dialogueBinding = CustomDialogueBinding.inflate(inflater, container, false)


        // Inflate the layout for this fragment
      myView=inflater.inflate(R.layout.fragment_setup, container, false)

        dialog= Dialog(requireContext())
        dialog.setContentView(R.layout.custom_dialogue)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)
        val ok=dialog.findViewById<Button>(R.id.okButton)
        val cancel=dialog.findViewById<Button>(R.id.cancelButton)
        val gpsButton=dialog.findViewById<RadioButton>(R.id.gpsRadioButton)
        val mapsButton=dialog.findViewById<RadioButton>(R.id.mapsRadioButton)
        dialog.show()
        gpsButton.setOnClickListener {

            if (mapsButton.isChecked) {
                mapsButton.setChecked(false)
                isGpsChecked=true
                println("el check:"+isGpsChecked)
            }


        }

        mapsButton.setOnClickListener {
            if (gpsButton.isChecked) {
                gpsButton.setChecked(false)
                isGpsChecked=false
                println("el check:"+isGpsChecked)

            }
        }
        ok.setOnClickListener {
            if(isGpsChecked==true)
            {
                println("el check:"+isGpsChecked)
                dialog.dismiss()
                // goToLocationSettings()
                getMyCurrentLocation()

            }
        }


        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
     // navigationView = view.findViewById(R.id.navigationView)

    }

    private fun goToLocationSettings() {
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        startActivity(intent)
    }
    @SuppressLint("MissingPermission")
    private fun getMyCurrentLocation() {
        if (checkPermission()) {
            if (isLocationEnabled()) {
                requestNewLocationData()

            } else {
                Toast.makeText(requireContext(), "Turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }

        private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun checkPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(
                    requireContext(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    }

        private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION

            ),
            PERISSION_ID

        )
    }

    private val mLocationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation: Location = locationResult.lastLocation
            lon = mLastLocation.longitude.toString()
            lan= mLastLocation.latitude.toString()
            navigateToHome()
            println("el lon="+lon)
            println("el lan="+lan)

        }
    }

    private fun navigateToHome() {

        val action=SetupFragmentDirections.actionSetupFragmentToHomePageFragment2(lon,lan)
        findNavController(myView).navigate(action)

        //findNavController(, R.id.fragmentView).navigate(action)
        //Navigation.findNavController(myView).navigate(action)
//        val action: FavoriteFragmentDirections.ActionFavoriteFragmentToMealDetailsFragment =
//            FavoriteFragmentDirections.actionFavoriteFragmentToMealDetailsFragment(mealName)
//        findNavController(view!!).navigate(action)
    }

    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        val myLocationRequest = LocationRequest()
        myLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        myLocationRequest.setInterval(0)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        mFusedLocationClient.requestLocationUpdates(
            myLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }
}