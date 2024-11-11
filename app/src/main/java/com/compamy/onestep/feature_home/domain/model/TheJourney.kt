package com.compamy.onestep.feature_home.domain.model

data class TheJourney(
    val  id :String,
    val title:String,
    val description:String,
    val startDate:Long,
    val endDate:Long,
    val places:List<ThePlace>

)
