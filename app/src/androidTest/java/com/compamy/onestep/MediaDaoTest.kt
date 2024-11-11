package com.compamy.onestep

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.compamy.onestep.feature_home.data.data_source.OneDatabase
import com.compamy.onestep.feature_home.data.data_source.dao.DaoJourney
import com.compamy.onestep.feature_home.data.data_source.dao.DaoMedia
import com.compamy.onestep.feature_home.data.data_source.dao.DaoPlace
import com.compamy.onestep.feature_home.data.data_source.entity.EntityJourney
import com.compamy.onestep.feature_home.data.data_source.entity.EntityMedia
import com.compamy.onestep.feature_home.data.data_source.entity.EntityPhoto
import com.compamy.onestep.feature_home.data.data_source.entity.EntityPlace
import com.compamy.onestep.feature_home.data.data_source.entity.EntityVideo
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class MediaDaoTest {

    private lateinit var database: OneDatabase
    private val journeyDao: DaoJourney by lazy {
        database.daoJourney
    }
    private val placeDao: DaoPlace by lazy {
        database.daoPlace
    }

    private val mediaDao: DaoMedia by lazy {
        database.mediaDao
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
    fun testInsertMedia()= runBlocking {
        val media =EntityMedia("0","uri","url","desc")
        mediaDao.insertMedia(media)
        val retrieveMedia = mediaDao.getMediaWithId("0")

        assert(media.id == retrieveMedia.id)

        val photo = EntityPhoto("00",retrieveMedia.id)
        val video =EntityVideo("01",retrieveMedia.id,100,50)

        mediaDao.insertPhoto(photo)
        mediaDao.insertVideo(video)

        val retrievePhoto = mediaDao.getPhotoWithId("00")
        val retrieveVideo = mediaDao.getvideoWithId("01")

        assert(photo.id == retrievePhoto.id)
        assert(video.id == retrieveVideo.id)
        assert(photo.mediaId == retrieveMedia.id)
        assert(video.mediaId == retrieveMedia.id)


        val retrieveMediaPhoto = mediaDao.getPhotoMedia(retrieveMedia.id)
        val retrieveMediaVideo = mediaDao.getvideoMedia(retrieveMedia.id)


        assert(retrieveMediaPhoto.media.id == retrieveMedia.id)
        assert(retrieveMediaVideo.media.id == retrieveMedia.id)


        assert(retrieveMediaPhoto.photo.id == retrievePhoto.id)
        assert(retrieveMediaVideo.video.id == retrieveVideo.id)







    }





    @After
    fun teardow()
    {
        database.close()
    }



}