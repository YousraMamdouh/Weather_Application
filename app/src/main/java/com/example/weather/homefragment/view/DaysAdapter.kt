package com.example.weather.homefragment.view


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weather.R
import com.example.weather.databinding.DayitemBinding
import com.example.weather.model.Daily
import java.text.SimpleDateFormat
import java.util.*


class DaysAdapter : ListAdapter<Daily, DaysAdapter.DailyViewHolder>(DaysDiffUtil()) {
    lateinit var context: Context
    lateinit var binding: DayitemBinding
    private val SHARED_PREF_NAME = "settingsPref"
    private val KEY_TEMP = "temp"

    val sharedPrefs by lazy {
        context.getSharedPreferences(
            SHARED_PREF_NAME, Context.MODE_PRIVATE
        )
    }

    inner class DailyViewHolder(var binding: DayitemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        context = parent.context
        val inflater: LayoutInflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DayitemBinding.inflate(inflater, parent, false)
        return DailyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {

        val currentObj = getItem(position)
        var day: String = getCurrentDay(currentObj.dt.toInt())
        holder.binding.day.text = day


        if ((sharedPrefs?.getString(KEY_TEMP, null).toString()).equals("metric")) {
            holder.binding.maxTemp.text = Math.ceil(currentObj.temp.max).toInt().toString() + "°C"
            holder.binding.minTemp.text = Math.ceil(currentObj.temp.min).toInt().toString() + "°C"

        } else {
            holder.binding.maxTemp.text =
                Math.ceil((currentObj.temp.max) * 1.8 + 32).toInt().toString() + "°F"
            holder.binding.minTemp.text =
                Math.ceil((currentObj.temp.min) * 1.8 + 32).toInt().toString() + "°F"

        }

//        holder.binding.maxTemp.text=Math.ceil(currentObj.temp.max).toInt().toString()+"°C"
//        holder.binding.minTemp.text=Math.ceil(currentObj.temp.min).toInt().toString()+"°C"
        //  holder.binding.weatherdesc.text=currentObj.weather[0].description
        //    holder.binding.dayLowTxt.text=Math.ceil(currentObj.temp.min).toInt().toString()+"°C"
        when (currentObj.weather[0].main) {
            "Clouds" -> holder.binding.dayIcon.setImageResource(R.drawable._cloudy)
            "Clear" -> holder.binding.dayIcon.setImageResource(R.drawable._clear)
            "Thunderstorm" -> holder.binding.dayIcon.setImageResource(R.drawable._thunderstorm)
            "Drizzle" -> holder.binding.dayIcon.setImageResource(R.drawable._drizzle)
            "Rain" -> holder.binding.dayIcon.setImageResource(R.drawable._rain)
            "Snow" -> holder.binding.dayIcon.setImageResource(R.drawable._snow)
            "Mist" -> holder.binding.dayIcon.setImageResource(R.drawable._mist)
            "Smoke" -> holder.binding.dayIcon.setImageResource(R.drawable._dust)
            "Haze" -> holder.binding.dayIcon.setImageResource(R.drawable.haze)
            "Dust" -> holder.binding.dayIcon.setImageResource(R.drawable._dust)
            "Fog" -> holder.binding.dayIcon.setImageResource(R.drawable._fog)
            "Sand" -> holder.binding.dayIcon.setImageResource(R.drawable._dust)
            "Ash" -> holder.binding.dayIcon.setImageResource(R.drawable.haze)
            "Squall" -> holder.binding.dayIcon.setImageResource(R.drawable._squall)
            "Tornado" -> holder.binding.dayIcon.setImageResource(R.drawable._tornado)
        }
        //Glide.with(context).load("https://openweathermap.org/img/wn/${currentObj.weather.get(0).icon}@2x.png").into(holder.binding.dayIcon)

    }
}

fun getCurrentDay(dt: Int): String {
    var date = Date(dt * 1000L)
    var sdf = SimpleDateFormat("d")
    sdf.timeZone = TimeZone.getDefault()
    var formatedData = sdf.format(date)
    var intDay = formatedData.toInt()
    var calendar = Calendar.getInstance()
    calendar.set(Calendar.DAY_OF_MONTH, intDay)
    var format = SimpleDateFormat("EEEE")
    return format.format(calendar.time)
}

class DaysDiffUtil : DiffUtil.ItemCallback<Daily>() {
    override fun areItemsTheSame(oldItem: Daily, newItem: Daily): Boolean {
        return oldItem.dt == newItem.dt
    }

    override fun areContentsTheSame(oldItem: Daily, newItem: Daily): Boolean {
        return oldItem == newItem
    }


}