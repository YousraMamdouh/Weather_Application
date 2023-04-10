package com.example.weather.utilities

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utilities : AppCompatActivity() {
    fun isConnectedToInternet(context: Context?):Boolean
    {
        val connectionManager: ConnectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectionManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return isConnected
    }



    fun changeLanguage(lang:String, context: Context){
        val config = context.resources.configuration

        val locale = Locale(lang)
        Locale.setDefault(locale)
        config.setLocale(locale)

        context.createConfigurationContext(config)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
        context.startActivity(Intent(context, context::class.java))

    }




    fun convertToArabic(value: String): String {
        return (value + "")
            .replace("1".toRegex(), "١").replace("2".toRegex(), "٢")
            .replace("3".toRegex(), "٣").replace("4".toRegex(), "٤")
            .replace("5".toRegex(), "٥").replace("6".toRegex(), "٦")
            .replace("7".toRegex(), "٧").replace("8".toRegex(), "٨")
            .replace("9".toRegex(), "٩").replace("0".toRegex(), "٠")
    }

    fun convertDateAndTimeToMills(timeAndDate: String?): Long {
        val sdf = SimpleDateFormat("dd-MM-yyyy hh:mm")
        val timeInMilliseconds: Long
        var mDate: Date? = null
        try {
            mDate = sdf.parse(timeAndDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        Log.i("TAG", "Date m datee: $mDate")
        timeInMilliseconds = mDate!!.time
        println("Date in milli :: $timeInMilliseconds")
        return timeInMilliseconds
    }



    fun convertTimeInMillesToMinutes(time:Long) : String{
        val myDate = Date(time)
        val formatter = SimpleDateFormat("hh:mm a")
        val myTime = formatter.format(myDate)
        return myTime
    }

    fun convertDateInMillsToDate(date:Long) : String{
        val myDate = Date(date)
        val formatter = SimpleDateFormat("dd/M/yyyy")
        val myTime = formatter.format(myDate)
        return myTime
    }



}