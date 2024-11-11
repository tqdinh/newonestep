package com.compamy.onestep.feature_home.domain.model


sealed class Media {
    data class ThePhoto(
        val id: String, val uri: String?, val url: String, val description: String
    ) : Media()

    data class TheVideo(
        val id: String,
        val uri: String?,
        val url: String?,
        val description: String?,
        val duration: Int?

    ) : Media()
}


