package com.example.weather.alertsFragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import com.example.weather.R
import com.example.weather.databinding.AlertDialogueBinding
import com.example.weather.databinding.CustomDialogueBinding
import com.example.weather.databinding.FragmentAlertsBinding
import com.example.weather.databinding.FragmentFavoritesBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*


class AlertsFragment : Fragment() {
    lateinit var dialogueBinding: AlertDialogueBinding
    lateinit var dialog: Dialog
    lateinit var text: TextView
    lateinit var myView: View
    lateinit var addAlertButton:FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog = Dialog(requireContext())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        var startTime = Calendar.getInstance().timeInMillis
        // Inflate the layout for this fragment

        myView = inflater.inflate(R.layout.fragment_alerts, container, false)


     //   dialogueBinding = AlertDialogueBinding.inflate(inflater, container, false)
        addAlertButton=myView.findViewById(R.id.aletrFloatingButton)
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
        val startTimeView= dialog.findViewById<TextView>(R.id.startTime)
        val endTimeView= dialog.findViewById<TextView>(R.id.endTime)


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
                //    dialogueBinding.startDate.setDate(currentTime)
                //dialogueBinding.startTime.setTime(currentTime)
            }
        }
        return myView
    }


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
//    fun setDate(timeInMilliSecond: Long){
//        text = getADateFormat(timeInMilliSecond)
//    }


}