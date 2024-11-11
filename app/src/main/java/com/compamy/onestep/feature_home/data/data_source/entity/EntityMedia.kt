package com.compamy.onestep.feature_home.data.data_source.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "media",
    )
data class EntityMedia(
    @PrimaryKey val id:String,
    val uri:String,
    val url:String,
    val description:String?
)


@Entity(tableName = "photos")
data class EntityPhoto(
    @PrimaryKey val id:String,
    val mediaId:String
)

data class EntityMediaPhoto(
    @Embedded val media:EntityMedia,
    @Relation(
        parentColumn = "id",
        entityColumn = "mediaId")
    val photo:EntityPhoto
)

@Entity(tableName = "videos")
data class EntityVideo(
    @PrimaryKey val id:String,
    val mediaId: String,
    val duration:Int,
    val resolution:Int
)

data class EntityMediaVideo(
    @Embedded val media: EntityMedia,
    @Relation(parentColumn = "id", entityColumn = "mediaId")
    val video:EntityVideo
)