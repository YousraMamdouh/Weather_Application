package com.example.weather.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.weather.model.FavoriteModel
import com.example.weather.model.WeatherModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

import org.hamcrest.MatcherAssert
import org.hamcrest.collection.IsEmptyCollection
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
@SmallTest
class WeatherDaoTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    lateinit var database: WeatherDatabase
    lateinit var dao: WeatherDao

    @Before
    fun init() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            WeatherDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.getProductsDAO()
    }

    @After
    fun tearDown() {


        database.close()
    }

    @Test
    fun getStoredCurrentWeather_retrieveCurrentWeather_returnObjectOfWeatherModel() =runBlocking {
            //Given
            //  val currentWeather= WeatherModel()

            //  dao.insertCurrentWeatherObject(currentWeather)
            //When
            val results = dao.getStoredCurrentWeather()
            //Then
        }

    @Test
    fun insertCurrentWeatherObject_InsertObjectInDataBase_returnProcessIsSuccessful() {

    }

    @Test
    fun getAllFavorites_retrieveListOfFavorites_ListReturnedIsAsInserted() = runBlocking {

        //Given
        val country_1 = FavoriteModel("Egypt", 1.1, 2.2)
        val country_2 = FavoriteModel("Italy", 3.1, 4.2)
        val country_3 = FavoriteModel("Germany", 5.1, 6.2)
        val country_4 = FavoriteModel("America", 7.1, 8.2)

        dao.insertToFavorites(country_1)
        dao.insertToFavorites(country_2)
        dao.insertToFavorites(country_3)
        dao.insertToFavorites(country_4)
        //When
        val results = dao.getAllFavorites().first()
        //Then
        MatcherAssert.assertThat(results.size, Is.`is`(4))
    }

    @Test
    fun insertToFavorites_InsertObjectInDataBase_returnProcessIsSuccessful()= runBlocking {

        //Given
        val country = FavoriteModel("Egypt", 1.1, 2.2)




        //When
        dao.insertToFavorites(country)
       //Then
        val results = dao.getAllFavorites().first()

        MatcherAssert.assertThat(results.get(0).locality, Is.`is`("Egypt"))
      MatcherAssert.assertThat(results.get(0).locality, IsNull.notNullValue())


    }

    @Test
    fun deleteFromFavorites_removeObjectFromDataBase_returnProcessIsSuccessful()=runBlocking{
        //Given
        val country = FavoriteModel("Egypt", 1.1, 2.2)
        dao.insertToFavorites(country)



        //When
        dao.deleteFromFavorites(country)
        //Then
        val results = dao.getAllFavorites().first()

        MatcherAssert.assertThat(results.size, Is.`is`(0))
        MatcherAssert.assertThat(results, IsEmptyCollection.empty())
    }
}