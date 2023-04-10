package com.example.weather.alertsFragment.view

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.alertsFragment.viewModel.AlertsViewModel
import com.example.weather.alertsFragment.viewModel.AlertsViewModelFactory
import com.example.weather.database.ConcreteLocalSource
import com.example.weather.databinding.AlertDialogueBinding
import com.example.weather.model.Repository
import com.example.weather.network.WeatherClient
import com.google.android.material.floatingactionbutton.FloatingActionButton

import java.util.*


class AlertsFragment : Fragment(), OnClickListener {
    lateinit var dialogueBinding: AlertDialogueBinding
    lateinit var dialog: Dialog
    lateinit var text: TextView
    lateinit var myView: View
    lateinit var alertsAdapter: AlertsAdapter
    lateinit var addAlertButton: FloatingActionButton
    lateinit var alertsRecyclerView: RecyclerView
    private lateinit var alertsViewModel: AlertsViewModel
    private lateinit var alertsViewModelFactory: AlertsViewModelFactory
    val SHARED_NAME = "Alert_Prefs"
    val RADIO_STATE = "Is_Dialogue"
    val sharedPreferences by lazy {
        activity?.getSharedPreferences(
            SHARED_NAME, Context.MODE_PRIVATE
        )
    }
    // val sharedPreferences = requireContext().getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        alertsAdapter = AlertsAdapter(this)
        alertsAdapter.notifyDataSetChanged()
        dialog = Dialog(requireContext())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        myView = inflater.inflate(R.layout.fragment_alerts, container, false)
        val editor = sharedPreferences?.edit()
        var startTime = Calendar.getInstance().timeInMillis
        // Inflate the layout for this fragment
        alertsRecyclerView = myView.findViewById(R.id.alertsRecyclerView)
        alertsRecyclerView.apply {
            this.adapter = alertsAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = RecyclerView.VERTICAL
            }
        }

        alertsViewModelFactory = AlertsViewModelFactory(
            Repository.getInstance(
                WeatherClient.getInstance(),
                ConcreteLocalSource(requireContext()), requireActivity()
            )
        )

        alertsViewModel = ViewModelProvider(
            this,
            alertsViewModelFactory
        ).get(AlertsViewModel::class.java)

        alertsViewModel.alerts.observe(requireActivity())
        {
            if (it != null)
                alertsAdapter.submitList(it)
            alertsAdapter.notifyDataSetChanged()
        }

        //   dialogueBinding = AlertDialogueBinding.inflate(inflater, container, false)
        addAlertButton = myView.findViewById(R.id.aletrFloatingButton)
        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.alert_dialogue)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(false)
        addAlertButton.setOnClickListener {

            dialog.show()
        }
        val saveButton = dialog.findViewById<Button>(R.id.saveButton)
        val cancelButton = dialog.findViewById<Button>(R.id.cancelButton)
        val startDateCard = dialog.findViewById<CardView>(R.id.startDateCard)
        val endDateCard = dialog.findViewById<CardView>(R.id.endDateCard)
        val startDateView = dialog.findViewById<TextView>(R.id.startDate)
        val endDateView = dialog.findViewById<TextView>(R.id.endDate)
        val alarmRadioButton = dialog.findViewById<RadioButton>(R.id.alarmRadioButton)
        val notifyRadioButton = dialog.findViewById<RadioButton>(R.id.notificationRadioButton)

        notifyRadioButton.setOnClickListener {
            if (alarmRadioButton.isChecked) {
                alarmRadioButton.setChecked(false)
                editor?.putBoolean(RADIO_STATE, false)?.apply()
            }
        }
        saveButton.setOnClickListener {
            if ((sharedPreferences?.getBoolean(RADIO_STATE, false) == true)) {
                if (!Settings.canDrawOverlays(requireContext())) {
                    val intent = Intent(
                        Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + requireContext().applicationContext.packageName)

                    )
                    dialog.dismiss()
                    startActivityForResult(intent, 1)
                }
            } else {
                dialog.dismiss()
            }

        }
        alarmRadioButton.setOnClickListener {

            if (notifyRadioButton.isChecked) {
                notifyRadioButton.setChecked(false)
                editor?.putBoolean(RADIO_STATE, true)?.apply()


            }


        }


        cancelButton.setOnClickListener {
            dialog.dismiss()
        }
//        dialogueBinding.cancelButton.setOnClickListener {
//            dialog.dismiss()
//        }
        startDateCard.setOnClickListener {

            println("dosty 3lya le")
            setAlarm(startTime) { currentTime ->
                startTime = currentTime

                //    dialogueBinding.startDate.setDate(currentTime)
                //dialogueBinding.startTime.setTime(currentTime)
            }
        }
        endDateCard.setOnClickListener {
            setAlarm(startTime) { currentTime ->
                startTime = currentTime
                //    dia
                //    logueBinding.startDate.setDate(currentTime)
                //dialogueBinding.startTime.setTime(currentTime)
            }
        }
        return myView
    }

//    private fun checkPermissionOfOverlay() {
//        if (!Settings.canDrawOverlays(requireContext())) {
//            val alertDialogueBuilder = MaterialAlertDialogBuilder(requireContext())
//            alertDialogueBuilder.setTitle("Display On Top")
//                .setMessage("You should allow to draw on top")
//                .setPositiveButton("Okay") { dialog: DialogInterface, : Int ->
//                }
//        }
//    }


    fun showDialogue() {
        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.alert_dialogue)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(false)
        dialog.show()

    }

    private fun setAlarm(minTime: Long, callback: (Long) -> Unit) {
        val color = ResourcesCompat.getColor(resources, R.color.black, requireActivity().theme)
        Calendar.getInstance().apply {
            this.set(Calendar.SECOND, 0)
            this.set(Calendar.MILLISECOND, 0)
            val datePickerDialog = DatePickerDialog(
                requireContext(), { _, year, month, day ->
                    this.set(Calendar.YEAR, year)
                    this.set(Calendar.MONTH, month)
                    this.set(Calendar.DAY_OF_MONTH, day)
                    val timePickerDialog = TimePickerDialog(
                        requireContext(), { _, hour, minute ->
                            this.set(Calendar.HOUR_OF_DAY, hour)
                            this.set(Calendar.MINUTE, minute)
                            callback(this.timeInMillis)
                        }, this.get(Calendar.HOUR_OF_DAY), this.get(Calendar.MINUTE), false
                    )
                    timePickerDialog.show()
                    timePickerDialog.setCancelable(false)
                    timePickerDialog.getButton(TimePickerDialog.BUTTON_NEGATIVE).setTextColor(color)
                    timePickerDialog.getButton(TimePickerDialog.BUTTON_POSITIVE).setTextColor(color)
                },

                this.get(Calendar.YEAR), this.get(Calendar.MONTH), this.get(Calendar.DAY_OF_MONTH)

            )
            datePickerDialog.datePicker.minDate = minTime
            datePickerDialog.show()
            datePickerDialog.setCancelable(false)
            datePickerDialog.getButton(TimePickerDialog.BUTTON_NEGATIVE).setTextColor(color)
            datePickerDialog.getButton(TimePickerDialog.BUTTON_POSITIVE).setTextColor(color)

        }
    }


    override fun onDeleteClick(id: Int) {
        TODO("Not yet implemented")
    }
}