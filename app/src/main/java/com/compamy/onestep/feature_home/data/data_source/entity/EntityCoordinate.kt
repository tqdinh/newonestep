package com.compamy.onestep.feature_home.data.data_source.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "coordinates",
    foreignKeys = [ForeignKey(
        entity = EntityPlace::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("id"),
        onDelete = ForeignKey.CASCADE
    )])
data class EntityCoordinate(
    @PrimaryKey val id:String,
    val placeId:String,
    val latitude:Double,
    val longitude:Double
)