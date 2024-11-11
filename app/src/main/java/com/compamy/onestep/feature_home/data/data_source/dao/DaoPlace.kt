package com.compamy.onestep.feature_home.data.data_source.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.compamy.onestep.feature_home.data.data_source.entity.EntityPlace
import com.compamy.onestep.feature_home.data.data_source.entity.PlaceWithMedias


@Dao
interface DaoPlace {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun insertPlace(place: EntityPlace)

    @Query("SELECT * FROM places WHERE id=:placeId")
    suspend fun getPlaceWithId(placeId:String):EntityPlace

    @Transaction
    @Query("SELECT * FROM places WHERE id =:placeId")
    suspend fun getPlaceWithMedia(placeId:String): PlaceWithMedias?

}