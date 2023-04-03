package com.example.weather.settingsFragment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weather.R
import com.example.weather.databinding.FragmentHomePageBinding
import com.example.weather.databinding.FragmentSetthingsBinding


class SettingsFragment : Fragment() {
    private var isButtonChecked=false;

    lateinit var binding: FragmentSetthingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding =FragmentSetthingsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tempSwitch.setOnClickListener{
            if(isButtonChecked==false){
                isButtonChecked=true
                println("ana dlwa2ty Fahrenhit")
            }

            else
            {
                isButtonChecked=false
                println("ana dlwa2ty celsius ")

            }
        }
    }
}