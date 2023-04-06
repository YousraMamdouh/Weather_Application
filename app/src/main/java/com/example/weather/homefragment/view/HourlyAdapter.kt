package com.example.weather.homefragment.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.databinding.HouritemBinding
import com.example.weather.model.Hourly
import java.text.SimpleDateFormat
import java.util.*


class HourlyAdapter : ListAdapter<Hourly, HourlyAdapter.HourlyViewHolder>(HoursDiffUtil()) {
    lateinit var context: Context
    lateinit var binding: HouritemBinding
    private val SHARED_PREF_NAME = "settingsPref"
    private val KEY_TEMP = "temp"

    val sharedPrefs by lazy {
     context. getSharedPreferences(
            SHARED_PREF_NAME, Context.MODE_PRIVATE
        )
    }

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

        val currentObj=getItem(position)
        var time:String= getCurrentTime(currentObj.dt.toInt())
        holder.binding.hour.text=time
        if ((sharedPrefs?.getString(KEY_TEMP, null).toString()).equals("metric")) {
            holder.binding.weather.text=Math.ceil(currentObj.temp).toInt().toString()+"°C"

        } else {

            holder.binding.weather.text=Math.ceil((currentObj.temp) * 1.8 + 32).toInt().toString()+"°F"

        }

        when(currentObj.weather[0].main){
            "Clouds" -> holder.binding.icon.setImageResource(R.drawable._cloudy)
            "Clear" -> holder.binding.icon.setImageResource(R.drawable._clear)
            "Thunderstorm" -> holder.binding.icon.setImageResource(R.drawable._thunderstorm)
            "Drizzle" -> holder.binding.icon.setImageResource(R.drawable._drizzle)
            "Rain" -> holder.binding.icon.setImageResource(R.drawable._rain)
            "Snow" -> holder.binding.icon.setImageResource(R.drawable._snow)
            "Mist" -> holder.binding.icon.setImageResource(R.drawable._mist)
            "Smoke" -> holder.binding.icon.setImageResource(R.drawable._dust)
            "Haze" -> holder.binding.icon.setImageResource(R.drawable.haze)
            "Dust" -> holder.binding.icon.setImageResource(R.drawable._dust)
            "Fog" -> holder.binding.icon.setImageResource(R.drawable._fog)
            "Sand" -> holder.binding.icon.setImageResource(R.drawable._dust)
            "Ash" -> holder.binding.icon.setImageResource(R.drawable.haze)
            "Squall" -> holder.binding.icon.setImageResource(R.drawable._squall)
            "Tornado" -> holder.binding.icon.setImageResource(R.drawable._tornado)
        }

       // Glide.with(context).load("https://openweathermap.org/img/wn/${currentObj.weather.get(0).icon}@2x.png").into(holder.binding.icon)
    }
    private fun getCurrentTime(dt: Int): String {
        var date= Date(dt*1000L)
        var sdf= SimpleDateFormat("hh:mm a")
        sdf.timeZone= TimeZone.getDefault()
        return sdf.format(date)

    }

}

class HoursDiffUtil : DiffUtil.ItemCallback<Hourly>() {
    override fun areItemsTheSame(oldItem: Hourly, newItem:Hourly): Boolean {
        return oldItem.dt == newItem.dt
    }

    override fun areContentsTheSame(oldItem:Hourly, newItem: Hourly): Boolean {
        return oldItem == newItem
    }
}