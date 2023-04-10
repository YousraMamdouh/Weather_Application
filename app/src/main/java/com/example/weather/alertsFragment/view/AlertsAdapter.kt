package com.example.weather.alertsFragment.view


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.AlarmItemBinding
import com.example.weather.databinding.FavoriteItemsBinding
import com.example.weather.model.AlertsModel
import com.example.weather.model.FavoriteModel
import com.example.weather.utilities.Utilities


class AlertsAdapter(val onClickListener: OnClickListener) : ListAdapter<AlertsModel, AlertsAdapter.AlertsViewHolder>(DaysDiffUtil()) {
    lateinit var context: Context
    lateinit var binding: AlarmItemBinding

    inner class AlertsViewHolder(var binding:AlarmItemBinding): RecyclerView.ViewHolder(binding.root) {
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlertsViewHolder {
        context=parent.context
        val inflater:LayoutInflater=parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding= AlarmItemBinding.inflate(inflater,parent,false)
        return AlertsViewHolder(binding)
    }
    override fun onBindViewHolder(holder: AlertsViewHolder, position: Int) {

        val currentObj=getItem(position)
//        holder.binding.countryNameView.text=currentObj.locality
//        holder.binding.layout.setOnClickListener {
//
//
//        }
//        holder.binding.deleteCountryButton.setOnClickListener {
//            onClickListener.onDeleteClick(currentObj)
//        }
        holder.binding.alarmStartDate.text="${Utilities.convertDateInMillsToDate(currentObj.startDate)}${Utilities.convertDateInMillsToDate(currentObj.startTime)}"
        holder.binding.alarmEndDate.text="${Utilities.convertDateInMillsToDate(currentObj.endDate)}${Utilities.convertDateInMillsToDate(currentObj.endTime)}"
        holder.binding.deleteButton.setOnClickListener {
            onClickListener.onDeleteClick(currentObj.id)
        }

    }
}

class DaysDiffUtil:DiffUtil.ItemCallback<AlertsModel>(){
    override fun areItemsTheSame(oldItem: AlertsModel, newItem: AlertsModel): Boolean {
        return oldItem.id==newItem.id
    }
    override fun areContentsTheSame(oldItem:AlertsModel, newItem:AlertsModel): Boolean {
        return oldItem.equals(newItem)
    }


}