package com.example.weather.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.weather.model.FavoriteModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
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
class LocalSourceTest {





    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    lateinit var database: WeatherDatabase
    lateinit var localSource: ConcreteLocalSource

    @Before
    fun inIt() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            WeatherDatabase::class.java
        ).allowMainThreadQueries().build()
       localSource = ConcreteLocalSource(ApplicationProvider.getApplicationContext())
    }

    @After
    fun tearDown() {
        database.close()
    }

//    @Test
//    fun getStoredCurrentWeatherObject() {
//    }
//
//    @Test
//    fun insertCurrentWeatherObject() {
//    }

    @Test
    fun insertToFavorites_InsertObjectInDataBase_returnProcessIsSuccessful()=runBlockingTest {
        //Given
        val country = FavoriteModel("Egypt", 1.1, 2.2)




        //When
       localSource.insertToFavorites(country)
        //Then
        val results = localSource.getAllStoredFavorites().first()

        MatcherAssert.assertThat(results.get(0).locality, Is.`is`("Egypt"))
        MatcherAssert.assertThat(results.get(0).locality, IsNull.notNullValue())
    }

    @Test
    fun deleteFromFavorites_removeObjectFromDataBase_returnProcessIsSuccessful()= runBlockingTest {

        //Given
        val country = FavoriteModel("Egypt", 1.1, 2.2)
       localSource.insertToFavorites(country)



        //When
        localSource.deleteFromFavorites(country)
        //Then
        val results = localSource.getAllStoredFavorites().first()

        MatcherAssert.assertThat(results.size, Is.`is`(0))
        MatcherAssert.assertThat(results, IsEmptyCollection.empty())
    }

    @Test
    fun getAllStoredFavorites_retrieveListOfFavorites_ListReturnedIsAsInserted()= runBlocking {
        //Given
        val country_1 = FavoriteModel("Egypt", 1.1, 2.2)
        val country_2 = FavoriteModel("Italy", 3.1, 4.2)
        val country_3 = FavoriteModel("Germany", 5.1, 6.2)
        val country_4 = FavoriteModel("America", 7.1, 8.2)

       localSource.insertToFavorites(country_1)
       localSource.insertToFavorites(country_2)
        localSource.insertToFavorites(country_3)
        localSource.insertToFavorites(country_4)
        //When
        val results = localSource.getAllStoredFavorites().first()
        //Then
        MatcherAssert.assertThat(results.size, Is.`is`(4))
    }
}