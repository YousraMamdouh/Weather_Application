//package com.example.weather.homefragment.view
//
//override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//    super.onViewCreated(view, savedInstanceState)
//    homeViewModel.getDataFromApi()
//    hourlyAdapter= HourlyAdapter()
//    binding.hourlyRv.apply {
//        this.adapter=hourlyAdapter
//        layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
//    }
//
//    dailyAdapter= DailyAdapter()
//    binding.dailyRv.apply {
//        this.adapter=dailyAdapter
//        layoutManager=LinearLayoutManager(context)
//    }
//    homeViewModel.apiData.observe(viewLifecycleOwner){
//        binding.homeCountryTxt.text=it.timezone
//        binding.homeTempTxt.text=Math.ceil(it.current.temp).toInt().toString()+"°C"
//        binding.homeDescTxt.text=it.current.weather.get(0).description
//        Glide.with(requireActivity()).load("https://openweathermap.org/img/wn/${it.current.weather.get(0).icon}@2x.png").into(binding.homeIconImg)
//        hourlyAdapter.submitList(it.hourly)
//        hourlyAdapter.notifyDataSetChanged()
//        dailyAdapter.submitList(it.daily)
//        dailyAdapter.notifyDataSetChanged()
//        binding.humidityTxt.text=it.current.humidity.toString()+"%"
//        binding.dewPointTxt.text="The dew point is ${Math.ceil(it.current.dew_point)} right now"
//        binding.windSpeedTxt.text=it.current.wind_speed.toString()+" km/h"
//        binding.pressureTxt.text=it.current.pressure.toString()+" hPa"
//        binding.cloudsTxt.text=it.current.clouds.toString()
//
//    }
//
//
//}


//*******************************************
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView.ViewHolder
//import com.bumptech.glide.Glide
//import com.example.weatherforecast.Model.Current
//import com.example.weatherforecast.Model.Daily
//import com.example.weatherforecast.databinding.DayItemBinding
//import com.example.weatherforecast.databinding.HourItemBinding
//import java.text.SimpleDateFormat
//import java.util.*
//
//
//class DailyAdapter:ListAdapter<Daily,DailyAdapter.DailyViewHolder>(DayDiffUtil()) {
//    lateinit var context: Context
//    lateinit var binding: DayItemBinding
//
//    inner class DailyViewHolder (var binding :DayItemBinding):ViewHolder(binding.root){
//    }
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
//        context=parent.context
//        val inflater:LayoutInflater=parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        binding= DayItemBinding.inflate(inflater,parent,false)
//        return DailyViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
//        val currentObj=getItem(position)
//        var day:String= getCurrentDay(currentObj.dt.toInt())
//        holder.binding.dayNameTxt.text=day
//        holder.binding.dayHighTxt.text=Math.ceil(currentObj.temp.max).toInt().toString()+"°C"
//        holder.binding.dayLowTxt.text=Math.ceil(currentObj.temp.min).toInt().toString()+"°C"
//        Glide.with(context).load("https://openweathermap.org/img/wn/${currentObj.weather.get(0).icon}@2x.png").into(holder.binding.dayImg)
//    }
//
//    fun getCurrentDay( dt: Int) : String{
//        var date= Date(dt*1000L)
//        var sdf= SimpleDateFormat("d")
//        sdf.timeZone= TimeZone.getDefault()
//        var formatedData=sdf.format(date)
//        var intDay=formatedData.toInt()
//        var calendar= Calendar.getInstance()
//        calendar.set(Calendar.DAY_OF_MONTH,intDay)
//        var format= SimpleDateFormat("EEEE")
//        return format.format(calendar.time)
//    }
//}
//
//class DayDiffUtil: DiffUtil.ItemCallback<Daily>(){
//    override fun areItemsTheSame(oldItem: Daily, newItem: Daily): Boolean {
//        return (oldItem.dt==newItem.dt)
//    }
//
//    override fun areContentsTheSame(oldItem: Daily, newItem: Daily): Boolean {
//        return oldItem==newItem
//    }
//
//
//}