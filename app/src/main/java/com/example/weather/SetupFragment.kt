package com.example.weather

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import com.example.weather.databinding.CustomDialogueBinding
import com.example.weather.databinding.FragmentHomePageBinding


class SetupFragment : Fragment() {
    lateinit var dialogueBinding: CustomDialogueBinding
    lateinit var dialog: Dialog
    private var isGpsChecked = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog = Dialog(requireContext())


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        println("gowa el on create ")
       dialogueBinding = CustomDialogueBinding.inflate(inflater, container, false)

        dialog= Dialog(requireContext())
        dialog.setContentView(R.layout.custom_dialogue)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)
        val ok=dialog.findViewById<Button>(R.id.okButton)
        val cancel=dialog.findViewById<Button>(R.id.cancelButton)
        val gpsButton=dialog.findViewById<RadioButton>(R.id.gpsRadioButton)
        val mapsButton=dialog.findViewById<RadioButton>(R.id.mapsRadioButton)

        gpsButton.setOnClickListener {

            if (mapsButton.isChecked) {
                mapsButton.setChecked(false)
                isGpsChecked=true
            }


        }

        mapsButton.setOnClickListener {
            if (gpsButton.isChecked) {
                gpsButton.setChecked(false)
                isGpsChecked=false
            }
        }
        dialog.show()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setup, container, false)
    }

}