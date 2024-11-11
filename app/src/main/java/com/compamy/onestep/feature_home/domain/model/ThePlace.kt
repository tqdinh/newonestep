package com.compamy.onestep.feature_home.domain.model

data class ThePlace(
    val id: String,
    val name: String,
    val description: String,
    val latitude:Double,
    val longitude:Double,
    val media: List<Media>
)