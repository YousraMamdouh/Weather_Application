package com.example.weather.model

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.weather.database.FakeLocalDataSource
import com.example.weather.network.FakeRemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.core.Is
import org.hamcrest.core.IsNull
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class RepositoryTest {
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
    private lateinit var remoteDataSource: FakeRemoteDataSource
    private lateinit var localDataSource: FakeLocalDataSource
    private lateinit var repository:Repository

    @Before
    fun setUp() {
        remoteDataSource= FakeRemoteDataSource(weatherResponse)
        localDataSource= FakeLocalDataSource(favoritesList)
        repository= Repository(remoteDataSource,localDataSource,ApplicationProvider.getApplicationContext())
    }



    @Test
    fun getCurrentLocationWeather_getWeatherObjectFromRetrofit_successProcess()= runBlocking {
        //Given
        val lat=31.2000917.toString()
        val lon=29.9187383.toString()
        val lang="en"
        val unit="metric"
        val validAppid="bbcb13e1d448621ffd8e565701972f6d"
        //When
     val result=   repository.getCurrentLocationWeather(
            lon = lon, lat =lat , lang = lang,
            unit = unit, apiKey = validAppid
        ).first()

        //Then
        assertThat(result?.code() as Int, Is.`is`(200))
        assertThat(result.body() as WeatherModel , IsNull.notNullValue())


    }
//
//    @Test
//    fun getStoredCurrentWeatherObjectFromDatabase() {
//    }
//
//    @Test
//    fun insertCurrentWeatherObject()= runBlocking() {
//        repository.insertCurrentWeatherObject(weatherResponse)
//        val result=repository.getStoredCurrentWeatherObjectFromDatabase()
//        assertThat(result.timezone,Is.`is`("Africa/Cairo"))
//    }

    @Test
    fun getStoredFavorites_retrieveFavoritesFromRoom_checkListSize()= runBlocking {

        //Given
        val itemList=favoritesList
        itemList.add(FavoriteModel(
            "France", lat = 31.2000917,
            lon = 29.9187383
        ))

        //When
      val result=  repository.getStoredFavorites()?.first()
        //Then
        assertThat(result?.size, Is.`is`(5))
        assertThat(result?.get(0)?.locality, Is.`is`("Egypt"))
    }

    @Test
    fun insertToFavorites_addItemToRoom_checkListSizeUpdated()= runBlocking {
        //Given
        val item= FavoriteModel(
            "Egypt", lat = 31.2000917,
            lon = 29.9187383
        )
        //When
        repository.insertToFavorites(item)
        //Then
        assertThat(favoritesList.size, Is.`is`(5))


    }

    @Test
    fun deleteFromFavorites_deleteFomRoom_checkListSizeUpdated()= runBlocking {
        //Given
        val item= FavoriteModel(
            "Egypt", lat = 31.2000917,
            lon = 29.9187383
        )
        //When
        repository.insertToFavorites(item)
        repository.deleteFromFavorites(item)
        repository.deleteFromFavorites(favoritesList.get(0))
        //Then
        assertThat(favoritesList.size, Is.`is`(3))
    }
}