package com.compamy.onestep.feature_home.data.data_source

import android.provider.ContactsContract.Contacts.Photo
import androidx.room.Database
import androidx.room.RoomDatabase
import com.compamy.onestep.feature_home.data.data_source.dao.DaoJourney
import com.compamy.onestep.feature_home.data.data_source.dao.DaoMedia
import com.compamy.onestep.feature_home.data.data_source.dao.DaoPlace
import com.compamy.onestep.feature_home.data.data_source.entity.EntityJourney
import com.compamy.onestep.feature_home.data.data_source.entity.EntityMedia
import com.compamy.onestep.feature_home.data.data_source.entity.EntityPhoto
import com.compamy.onestep.feature_home.data.data_source.entity.EntityPlace
import com.compamy.onestep.feature_home.data.data_source.entity.EntityVideo
import com.compamy.onestep.feature_home.data.data_source.entity.PlacesMediasCrossRef


@Database(
    entities = [EntityJourney::class,
        EntityPlace::class,
        EntityMedia::class,
        PlacesMediasCrossRef::class,
        EntityPhoto::class,
        EntityVideo::class
    ],
    version = 1
)
abstract class OneDatabase : RoomDatabase() {

    abstract val daoJourney: DaoJourney
    abstract val daoPlace: DaoPlace
    abstract val mediaDao:DaoMedia


    companion object {
        const val DATABASE_NAME = "one_db"
    }
}