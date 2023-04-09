package com.example.weather.homefragment.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.MainDispatcherRule
import com.example.weather.database.FakeLocalDataSource
import com.example.weather.model.FavoriteModel
import com.example.weather.model.Repository
import com.example.weather.model.RepositoryInterface
import com.example.weather.model.WeatherModel
import com.example.weather.network.FakeRemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.pauseDispatcher
import kotlinx.coroutines.test.resumeDispatcher
import org.hamcrest.core.IsNull
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class CurrentWeatherViewModelTest {
    @get:Rule
    val instance= InstantTaskExecutorRule()
    @get:Rule
    val mainDispatcherRule= MainDispatcherRule()
    private lateinit var remoteDataSource: FakeRemoteDataSource
    private lateinit var localDataSource: FakeLocalDataSource
    private lateinit var viewModel: CurrentWeatherViewModel
    private lateinit var repository: RepositoryInterface
    private var weatherResponse = WeatherModel(
        current = null,
        lat = 31.2000917,
        lon = 29.9187383,
        timezone_offset = 55,
        timezone = "Africa/Cairo",
        hourly = emptyList(),
        daily = emptyList()

    )
    var favoritesList: MutableList<FavoriteModel> = mutableListOf<FavoriteModel>(

        FavoriteModel(
            "Egypt", lat = 31.2000917,
            lon = 29.9187383
        ),
        FavoriteModel(
            "Italy", lat = 544.2000917,
            lon = 33.9187383
        ), FavoriteModel(
            "Germany", lat = 31.2000917,
            lon = 19.9187383
        ), FavoriteModel(
            "America", lat = 131.2000917,
            lon = 229.9187383
        )
    )
    @Before
    fun setUp() {

        remoteDataSource= FakeRemoteDataSource(weatherResponse)
        localDataSource= FakeLocalDataSource(favoritesList)
        repository= Repository(remoteDataSource,localDataSource, ApplicationProvider.getApplicationContext())
       viewModel= CurrentWeatherViewModel(repository)
    }

    @Test
    fun getOnlineWeather_callWeatherObjectFromAPI_returnWeatherObject() {
        //Given arguments of weatherModel
        val lat=31.2000917.toString()
        val lon=29.9187383.toString()
        val lang="en"
        val unit="metric"
        val validAppid="bbcb13e1d448621ffd8e565701972f6d"

        //When calling weatherModel Object

        val result=viewModel.getCurrentWeather(lat,lon,lang,validAppid,unit)
        mainDispatcherRule.resumeDispatcher()
        //Then The result must be not null
        assertThat(result,IsNull.notNullValue())
    }

    @Test
    fun getOfflineWeather_retrieveWeatherObjectFromDataBase_ProcessSucceeded() {
        mainDispatcherRule.pauseDispatcher()
        //When retrieving weatherModel Object

        val result=viewModel.getCurrentWeatherObjectFromDatabase()
        mainDispatcherRule.resumeDispatcher()
        //Then The object must be inserted

        assertThat(result,IsNull.notNullValue())
    }

    @Test
    fun getCurrentWeather_callWeatherObjectFromAPI_returnWeatherObject()= runBlocking {
        mainDispatcherRule.pauseDispatcher()
        //Given arguments of weatherModel
        val lat=31.2000917.toString()
        val lon=29.9187383.toString()
        val lang="en"
        val unit="metric"
        val validAppid="bbcb13e1d448621ffd8e565701972f6d"

        //When calling weatherModel Object

        val result=viewModel.getCurrentWeather(lat,lon,lang,validAppid,unit)
        mainDispatcherRule.resumeDispatcher()
        //Then The result must be not null
        assertThat(result,IsNull.notNullValue())
    }

    @Test
    fun getCurrentWeatherObjectFromDatabase_retrieveWeatherObjectFromDataBase_ProcessSucceeded() {
        mainDispatcherRule.pauseDispatcher()
        //When retrieving weatherModel Object

        val result=viewModel.getCurrentWeatherObjectFromDatabase()
        mainDispatcherRule.resumeDispatcher()
        //Then The object must be inserted

        assertThat(result,IsNull.notNullValue())
    }

    @Test
    fun insertCurrentWeather_addCurrentWeatherObjectToDataBase_processSucceeded() {
        mainDispatcherRule.pauseDispatcher()
        //Given arguments of weatherModel
        val lat=31.2000917
        val lon=29.9187383


        //When inserting the object in database

        val result=viewModel.insertCurrentWeather(WeatherModel(lat=lat, lon = lon, timezone = "Egypt"))
        mainDispatcherRule.resumeDispatcher()
        //Then The object must be inserted

        assertThat(result,IsNull.notNullValue())
    }
}