package com.compamy.onestep.feature_home.data.data_source.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.compamy.onestep.feature_home.data.data_source.entity.EntityMedia
import com.compamy.onestep.feature_home.data.data_source.entity.EntityMediaPhoto
import com.compamy.onestep.feature_home.data.data_source.entity.EntityMediaVideo
import com.compamy.onestep.feature_home.data.data_source.entity.EntityPhoto
import com.compamy.onestep.feature_home.data.data_source.entity.EntityVideo


@Dao
interface DaoMedia {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun insertMedia(media: EntityMedia)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun insertPhoto(photo: EntityPhoto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun insertVideo(video: EntityVideo)


    @Query("SELECT * from media WHERE id=:mediaId")
   suspend fun getMediaWithId(mediaId:String):EntityMedia

    @Query("SELECT * from photos WHERE id=:mediaId")
    suspend fun getPhotoWithId(mediaId:String):EntityPhoto


    @Query("SELECT * from videos WHERE id=:mediaId")
    suspend fun getvideoWithId(mediaId:String):EntityVideo


    @Query("SELECT * FROM media WHERE id=:id")
    suspend fun getPhotoMedia(id:String):EntityMediaPhoto

    @Query("SELECT * FROM media WHERE id=:id")
    suspend fun getvideoMedia(id:String):EntityMediaVideo

}