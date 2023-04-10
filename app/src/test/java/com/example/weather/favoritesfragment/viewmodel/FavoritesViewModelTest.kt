package com.example.weather.favoritesfragment.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.MainDispatcherRule
import com.example.myweatherapp.getOrAwaitValue

import com.example.weather.database.FakeLocalDataSource
import com.example.weather.model.*
import com.example.weather.network.FakeRemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.pauseDispatcher
import kotlinx.coroutines.test.resumeDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.Is
import org.hamcrest.core.IsNull
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class FavoritesViewModelTest {
    @get:Rule

    val instance= InstantTaskExecutorRule()
    @get:Rule
    val mainDispatcherRule= MainDispatcherRule()
    private lateinit var remoteDataSource: FakeRemoteDataSource
    private lateinit var localDataSource: FakeLocalDataSource
    private lateinit var favoriteViewModel:FavoritesViewModel
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
        favoriteViewModel= FavoritesViewModel(repository)
    }


    @Test
    fun getFavorites_retrieveFavoritesFromDatabase_returnSuccessfully()= runBlockingTest {
        mainDispatcherRule.pauseDispatcher()
        //When
        val result = favoriteViewModel.favorites.getOrAwaitValue {  }
        mainDispatcherRule.resumeDispatcher()
        //Then
        assertThat(result, IsNull.notNullValue())

    }

    @Test
    fun insetToFavorites_insertItemInRoom_processSucceeded() {
        //pause dispatcher to verify initial value
        mainDispatcherRule.pauseDispatcher()
        //When
        favoriteViewModel.insetToFavorites(   FavoriteModel(
            "Egypt", lat = 31.2000917,
            lon = 29.9187383
        ))
        val result =favoriteViewModel.favorites.getOrAwaitValue {  }
        mainDispatcherRule.resumeDispatcher()
        //Then
        assertThat(result, IsNull.notNullValue())
        assertThat(result.size, Is.`is`(5))
    }

    @Test
    fun deleteFromFavorites_removeObjectFromDatabase_processSucceeded() {
        //pause dispatcher to verify initial value
        mainDispatcherRule.pauseDispatcher()

        //When
        favoriteViewModel.deleteFromFavorites(favoritesList.get(0))
        val result =favoriteViewModel.favorites.getOrAwaitValue {  }
        mainDispatcherRule.resumeDispatcher()
        //Then
        assertThat(result, IsNull.notNullValue())
        assertThat(result.size, Is.`is`(3))
    }
}