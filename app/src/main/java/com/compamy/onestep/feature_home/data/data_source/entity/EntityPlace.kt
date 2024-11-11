package com.compamy.onestep.feature_home.data.data_source.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "places")
data class EntityPlace(
    @PrimaryKey val id: String,
    val journeyId: String,
    val name: String,
    val description: String,
    val latitude:Double,
    val longitude:Double
)


@Entity( primaryKeys = ["placeId","mediaId"],
    foreignKeys = [
        ForeignKey(entity = EntityPlace::class, parentColumns = ["id"], childColumns = ["mediaId"]),
        ForeignKey(entity = EntityMedia::class, parentColumns = ["id"], childColumns = ["placeId"])

    ]
)
data class PlacesMediasCrossRef(
    val placeId:String,
    val mediaId:String
)


data class PlaceWithMedias(
    @Embedded val place: EntityPlace,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(PlacesMediasCrossRef::class, parentColumn = "placeId", entityColumn = "mediaId")
    )
    val mediaList: List<EntityMedia>
)

data class MediaWithPlaces(
    @Embedded val media: EntityMedia,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(PlacesMediasCrossRef::class, parentColumn = "mediaId", entityColumn = "placeId")
    )
    val places: List<EntityPlace>
)