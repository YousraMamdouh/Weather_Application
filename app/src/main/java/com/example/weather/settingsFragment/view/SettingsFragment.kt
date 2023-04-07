package com.example.weather.settingsFragment.view

import android.content.Context.MODE_PRIVATE
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.example.weather.databinding.FragmentSetthingsBinding
import com.example.weather.utilities.LocaleHelper
import java.util.*


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
            SHARED_PREF_NAME,MODE_PRIVATE
        )
    }
//val prefs: SharedPreferences? = activity?.getSharedPreferences("general_settings", Context.MODE_PRIVATE)
//    var lanSettings = prefs!!.getString("language", null)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
//        if ((sharedPrefs?.getString(KEY_LANG, "null").toString()).equals("ar")) {
//            language = "ar"
//            LocaleHelper.setLocale(requireContext(), "ar");
//            context?.getResources();
//            //   messageView.setText(resources.getString(R.string.language));
//
//        } else if ((sharedPrefs?.getString(KEY_LANG, "null").toString()).equals("en")) {
//            language= "en"
//            LocaleHelper.setLocale(requireContext(), "en");
//            context?.getResources();
//
//        }
        println("lesa da5l settings:${sharedPrefs?.getString(KEY_LANG, "null").toString()}")
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
            binding.languageSwitch.isChecked = true
        if (!location.equals("gps"))
            binding.locationSwitch.isChecked = true
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        Log.i(TAG,"eli 2areto ${sharedPrefs?.getString(KEY_TEMP, "null").toString()}")
//        Log.i(TAG,"ha3'yaro ahoh")
//        sharedPrefs?.edit()?.putString(KEY_TEMP, "Fahrenheit")?.apply()
//        Log.i(TAG,"dlwa2ty b2a ${sharedPrefs?.getString(KEY_TEMP, "null").toString()}")


        binding.tempSwitch.setOnClickListener{
            println("on switch clic;:${binding.tempSwitch.isChecked}")
        }

        binding.tempSwitch.setOnClickListener {
            if ((sharedPrefs?.getString(KEY_TEMP, "null").toString()).equals("metric")) {
                sharedPrefs?.edit()?.putString(KEY_TEMP, "Fahrenheit")?.apply()
            } else if ((sharedPrefs?.getString(KEY_TEMP, "null").toString()).equals("Fahrenheit")) {
                sharedPrefs?.edit()?.putString(KEY_TEMP, "metric")?.apply()
            }
        }
        binding.speedSwitch.setOnClickListener{
            if ((sharedPrefs?.getString(KEY_SPEED, "null").toString()).equals("miles")) {
                sharedPrefs?.edit()?.putString(KEY_SPEED, "meters")?.apply()
            } else if ((sharedPrefs?.getString(KEY_SPEED, "null").toString()).equals("meters")) {
                sharedPrefs?.edit()?.putString(KEY_SPEED, "miles")?.apply()
            }
        }
        binding.languageSwitch.setOnClickListener {
            if ((sharedPrefs?.getString(KEY_LANG, "null").toString()).equals("en")) {
                println("condetion eno eng:${sharedPrefs?.getString(KEY_LANG, "null").toString()}")
                sharedPrefs?.edit()?.putString(KEY_LANG, "ar")?.apply()
            } else if ((sharedPrefs?.getString(KEY_LANG, "null").toString()).equals("ar")) {
                println("condetion eno arabic :${sharedPrefs?.getString(KEY_LANG, "null").toString()}")
                sharedPrefs?.edit()?.putString(KEY_LANG, "en")?.apply()
            }
        //    changeLanguageAndLayout(language)

//           LocaleHelper.setLocale(requireContext(), language);
//         requireActivity().getResources();
          //  messageView.setText(resources.getString(R.string.language));

        }
        binding.locationSwitch.setOnClickListener {
            if ((sharedPrefs?.getString(KEY_LOCATION, "null").toString()).equals("gps")) {
                sharedPrefs?.edit()?.putString(KEY_LANG, "maps")?.apply()
            } else if ((sharedPrefs?.getString(KEY_LOCATION, "null").toString()).equals("maps")) {
                sharedPrefs?.edit()?.putString(KEY_LANG, "gps")?.apply()
            }
        }

    }
//    private fun changeLanguageAndLayout(language: String) {
//
//        val locale = Locale(language)
//        Locale.setDefault(locale)
//        val resources = context?.resources
//        val configuration = Configuration()
//        configuration.setLocale(locale)
//        resources?.updateConfiguration(configuration, resources.displayMetrics)
//
//        ViewCompat.setLayoutDirection(requireActivity().window.decorView, if (language == "ar") ViewCompat.LAYOUT_DIRECTION_RTL else ViewCompat.LAYOUT_DIRECTION_LTR)
//
//       // settingsViewModel.putBooleanInSharedPreferences("isLayoutChangedBySettings", true)
//
//        activity?.recreate()
//
//    }
}