package com.compamy.onestep

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.compamy.onestep.feature_home.data.data_source.OneDatabase
import com.compamy.onestep.feature_home.data.data_source.dao.DaoJourney
import com.compamy.onestep.feature_home.data.data_source.dao.DaoPlace
import com.compamy.onestep.feature_home.data.data_source.entity.EntityJourney
import com.compamy.onestep.feature_home.data.data_source.entity.EntityPlace
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class PlaceDaoTest {

    private lateinit var database: OneDatabase
    private val journeyDao: DaoJourney by lazy {
        database.daoJourney
    }
    private val placeDao: DaoPlace by lazy {
        database.daoPlace
    }

    @Before
    fun setup()
    {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            OneDatabase::class.java
        ).build()
    }

    @Test
    fun testPlaceMedia() = runBlocking {


        val journey = EntityJourney("1", "one", "desc", 100L, 100L)
        journeyDao.insertJourney(journey)

        val retrivedJourney =journeyDao.getJourneyById("1")

        assert(retrivedJourney!=null)

        val place0 = EntityPlace("00",retrivedJourney!!.id,"place00","description",100.0,100.0)
        val place1 = EntityPlace("01",retrivedJourney!!.id,"place01","description",101.0,101.0)
        placeDao.insertPlace(place0)
        placeDao.insertPlace(place1)

        val retrievePlace00 = placeDao.getPlaceWithId("00")
        val retrievePlace01 = placeDao.getPlaceWithId("01")

        assert(retrievePlace00!=null)
        assert(retrievePlace01!=null)

        assert(retrievePlace00.id=="00")
        val journeyWithPlaces = journeyDao.getJourneyWithPlaces(retrivedJourney.id)

        assert( journeyWithPlaces!=null)
        assert( journeyWithPlaces!!.places!=null)

        journeyWithPlaces.places.forEachIndexed{i,e->
            assert(e.id == "0${i}" )
        }




    }





    @After
    fun teardow()
    {
        database.close()
    }



}