package com.example.weather.network

import ApiService
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.weather.model.WeatherModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.core.Is
import org.hamcrest.core.IsNull
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class ApiServiceTest {

    @get:Rule
    var instantExecutorRule=InstantTaskExecutorRule()
     val weatherService: ApiService=
        RetrofitHelper.retrofitInstance.create(ApiService::class.java)




    @Test
    fun getCurrentWeather_retrieveWeatherObjectFromAPI_objectReturnedSuccessfully()= runBlocking {
        //given
        val lat=31.2000917.toString()
        val lon=29.9187383.toString()
        val lang="en"
        val unit="metric"
        val validAppid="bbcb13e1d448621ffd8e565701972f6d"
        val emptyAppid=""


        //when
        val response_success=weatherService.getCurrentWeather(
            lang = lang, lat = lat, lon =lon,
            appid = validAppid, unit = "metric"
        )
        val response_fail=weatherService.getCurrentWeather(
            lang =lang, lat =lat, lon = lon,
            appid = emptyAppid, unit = unit
        )
        //then


        assertThat(response_success?.code() as Int, Is.`is`(200))
        //401 mean un authorized
        assertThat(response_fail?.code() as Int, Is.`is`(401))
         assertThat(response_success.body() as WeatherModel , IsNull.notNullValue())


    }
}