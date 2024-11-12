package com.compamy.onestep.util

sealed class Screen (val route:String){
    object HomeScreen:Screen("home")
    object MapScreen:Screen("map")
    object RecordScreen:Screen("record")
    object NotificationScreen:Screen("notification")
    object AccountScreen:Screen("account")
    object CameraScreen:Screen("camera")
    object JourneyDetailScreen:Screen("journey_detail")
}