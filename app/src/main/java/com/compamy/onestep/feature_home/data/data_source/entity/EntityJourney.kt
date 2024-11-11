package com.compamy.onestep.feature_home.data.data_source.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "journeys")
data class EntityJourney(
    @PrimaryKey
    val id:String,
    val title:String,
    val description:String?,
    val startDate:Long,
    val endDate:Long
)

data class JourneyWithPlaces(
    @Embedded val journey: EntityJourney,
    @Relation(
        parentColumn = "id",
        entityColumn = "journeyId"
    )
    val places:List<EntityPlace> = listOf()
)