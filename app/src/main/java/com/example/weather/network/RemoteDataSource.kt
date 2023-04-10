//package com.example.weather.network
//
//import ApiService
//import android.content.Context
//import com.example.mvvn.network.RemoteSource
//import com.example.weather.model.WeatherModel
//import retrofit2.Response
//
//
//class RemoteDataSource private constructor(context: Context) : RemoteSource {
//
//    companion object {
//        @Volatile
//        private var remoteDataSourceInstance: RemoteDataSource? = null
//
//        @Synchronized
//        fun getInstance(context: Context): RemoteDataSource {
//            if (remoteDataSourceInstance == null) {
//                remoteDataSourceInstance = RemoteDataSource(context)
//            }
//            return remoteDataSourceInstance!!
//        }
//    }
//
//  //  private val weatherRetrofit = RetrofitHelper.getRetrofitInstance(ApiService.BASE_URL_WEATHER)
//    private val weatherRetrofit = RetrofitHelper.retrofitInstance
//    private val weatherApiService = weatherRetrofit.create(ApiService::class.java)
//
//
//
//
//    override suspend fun getCurrentWeather(
//        lat: String,
//        lon: String,
//        lang: String,
//        id: String,
//        unit: String,
//    ): Response<WeatherModel> {
//  return weatherApiService.getCurrentWeather(lat,lon,lang,id,unit)
//    }
//}