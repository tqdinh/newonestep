package com.compamy.onestep.feature_home.data.data_source.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.compamy.onestep.feature_home.data.data_source.entity.EntityJourney
import com.compamy.onestep.feature_home.data.data_source.entity.JourneyWithPlaces

@Dao
interface DaoJourney {

    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJourney(jouuney:EntityJourney)

    @Query("SELECT * FROM journeys")
    suspend fun getAllJourney():List<EntityJourney>

    @Query("SELECT * FROM journeys WHERE id =:journeyId")
    suspend fun getJourneyById(journeyId:String):EntityJourney?

    @Transaction
    @Query("SELECT * FROM journeys WHERE ID =:journeyId")
    suspend fun getJourneyWithPlaces(journeyId:String):JourneyWithPlaces?
}