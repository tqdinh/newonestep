package com.compamy.onestep.feature_record.presentation

sealed class JourneyActions {
    data class ToggleTracking(val value:Boolean) :JourneyActions()
    data class TrackingLoading(val value:Boolean):JourneyActions()
    data class TrackingStarted(val value:Boolean):JourneyActions()
}