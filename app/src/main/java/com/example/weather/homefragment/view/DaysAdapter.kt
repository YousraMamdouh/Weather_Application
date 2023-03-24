package com.example.weather.homefragment.view


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weather.databinding.DayitemBinding
import com.example.weather.databinding.HouritemBinding
import com.example.weather.model.Current
import com.example.weather.model.Daily
import com.example.weather.model.Hourly



class DaysAdapter:ListAdapter<Current, DaysAdapter.DailyViewHolder>(HoursDiffUtil()) {
    lateinit var context: Context
    lateinit var binding: DayitemBinding
    inner class DailyViewHolder(var binding:DayitemBinding): RecyclerView.ViewHolder(binding.root) {
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        context=parent.context
        val inflater:LayoutInflater=parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding= DayitemBinding.inflate(inflater,parent,false)
        return DailyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        val currentObj=getItem(position)
       // var time:String= getCurrentTime(currentObj.dt.toInt())

        holder.binding.weatherdesc.text=currentObj.weather[position].description
        holder.binding.weatherTemp.text=Math.ceil(currentObj.temp).toInt().toString()+"°C"
        Glide.with(context).load("https://openweathermap.org/img/wn/${currentObj.weather.get(0).icon}@2x.png").into(holder.binding.dayIcon)
//        Glide.with(context).load(currentObj.thumbnail).into(holder.binding.productImg)
//        holder.binding.productName.text=currentObj.title
//        holder.binding.productLayout.setOnClickListener {  onClick(currentObj)}
    }
}
class DaysDiffUtil:DiffUtil.ItemCallback<Daily>(){
    override fun areItemsTheSame(oldItem: Daily, newItem: Daily): Boolean {
        return oldItem.dt==newItem.dt
    }
    override fun areContentsTheSame(oldItem: Daily, newItem:Daily): Boolean {
        return oldItem==newItem
    }


}