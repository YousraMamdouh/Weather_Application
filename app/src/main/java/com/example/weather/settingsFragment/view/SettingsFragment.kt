package com.example.weather.settingsFragment.view

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.weather.R
import com.example.weather.databinding.FragmentSetthingsBinding


class SettingsFragment : Fragment() {
    private var isButtonChecked = false;

    //
   val TAG:String ="mysettings"
    lateinit var binding: FragmentSetthingsBinding
    private val SHARED_PREF_NAME = "settingsPref"
    private val KEY_TEMP = "temp"
    private val KEY_LANG = "language"
    private val KEY_SPEED = "speed"
    private val KEY_LOCATION = "location"

    lateinit var tempreture: String
    lateinit var speed: String
    lateinit var language: String
    lateinit var location: String
//    val sharedPrefs = activity?.getSharedPreferences(SHARED_PREF_NAME,
//       MODE_PRIVATE
//    )
    val sharedPrefs by lazy {
        activity?.getSharedPreferences(
            SHARED_PREF_NAME, Context.MODE_PRIVATE
        )
    }
//val prefs: SharedPreferences? = activity?.getSharedPreferences("general_settings", Context.MODE_PRIVATE)
//    var lanSettings = prefs!!.getString("language", null)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSetthingsBinding.inflate(inflater, container, false)
        tempreture = sharedPrefs?.getString(KEY_TEMP, "null").toString()
        speed = sharedPrefs?.getString(KEY_SPEED, "null").toString()
        language = sharedPrefs?.getString(KEY_LANG, "null").toString()
        location = sharedPrefs?.getString(KEY_LOCATION, "null").toString()
        if (!tempreture.equals("metric"))
            binding.tempSwitch.isChecked = true
        if (!speed.equals("miles"))
            binding.speedSwitch.isChecked = true
        if (!language.equals("en"))
            binding.labhuageSwitch.isChecked = true
        if (!location.equals("gps"))
            binding.locationSwitch.isChecked = true
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPrefs?.edit()?.putString(KEY_TEMP, "Fahrenheit")?.apply()
        binding.tempSwitch.setOnClickListener{
            println("on switch clic;:${binding.tempSwitch.isChecked}")
        }

//        binding.tempSwitch.setOnClickListener {
//            if (binding.tempSwitch.isChecked==false) {
//                sharedPrefs?.edit()?.putString(KEY_TEMP, "Fahrenheit")?.apply()
//                Log.i(TAG,"dlwa2ty ktabt ${sharedPrefs?.getString(KEY_TEMP, "null").toString()}")
//            } else if (binding.tempSwitch.isChecked==true) {
//                sharedPrefs?.edit()?.putString(KEY_TEMP, "metric")?.apply()
//            }
//        }
//        binding.speedSwitch.setOnClickListener{
//            if (binding.speedSwitch.isChecked==false) {
//                sharedPrefs?.edit()?.putString(KEY_SPEED, "meters")?.apply()
//            } else if (binding.speedSwitch.isChecked==true) {
//                sharedPrefs?.edit()?.putString(KEY_SPEED, "miles")?.apply()
//            }
//        }
//        binding.labhuageSwitch.setOnClickListener {
//            if (binding.labhuageSwitch.isChecked==false) {
//                sharedPrefs?.edit()?.putString(KEY_LANG, "ar")?.apply()
//            } else if (binding.labhuageSwitch.isChecked==true) {
//                sharedPrefs?.edit()?.putString(KEY_LANG, "en")?.apply()
//            }
//        }
//        binding.locationSwitch.setOnClickListener {
//            if (binding.locationSwitch.isChecked==false) {
//                sharedPrefs?.edit()?.putString(KEY_LANG, "maps")?.apply()
//            } else if (binding.locationSwitch.isChecked==true) {
//                sharedPrefs?.edit()?.putString(KEY_LANG, "gps")?.apply()
//            }
//        }

    }
}