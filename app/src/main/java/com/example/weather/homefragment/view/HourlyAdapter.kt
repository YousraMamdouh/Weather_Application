package com.example.weather.homefragment.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weather.databinding.HouritemBinding
import com.example.weather.model.Current
import com.example.weather.model.Hourly
import java.text.SimpleDateFormat
import java.util.*


class HourlyAdapter : ListAdapter<Current, HourlyAdapter.HourlyViewHolder>(HoursDiffUtil()) {
    lateinit var context: Context
    lateinit var binding: HouritemBinding

    inner class HourlyViewHolder(var binding: HouritemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        context = parent.context
        val inflater: LayoutInflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = HouritemBinding.inflate(inflater, parent, false)
        return HourlyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {
        val currentObj = getItem(position)
        var time:String= getCurrentTime(currentObj.dt.toInt())

        holder.binding.hour.text=time
        holder.binding.weather.text=Math.ceil(currentObj.temp).toInt().toString()+"°C"
        Glide.with(context).load("https://openweathermap.org/img/wn/${currentObj.weather.get(0).icon}@2x.png").into(holder.binding.icon)

//        Glide.with(context).load(currentObj.thumbnail).into(holder.binding.productImg)
//        holder.binding.productName.text=currentObj.title
//        holder.binding.productLayout.setOnClickListener {  onClick(currentObj)}
    }
    private fun getCurrentTime(dt: Int): String {
        var date= Date(dt*1000L)
        var sdf= SimpleDateFormat("hh:mm a")
        sdf.timeZone= TimeZone.getDefault()
        return sdf.format(date)

    }
}

class HoursDiffUtil : DiffUtil.ItemCallback<Hourly>() {
    override fun areItemsTheSame(oldItem: Hourly, newItem: Hourly): Boolean {
        return oldItem.dt == newItem.dt
    }

    override fun areContentsTheSame(oldItem: Hourly, newItem: Hourly): Boolean {
        return oldItem == newItem
    }
}