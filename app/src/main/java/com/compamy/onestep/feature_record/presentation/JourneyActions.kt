package com.compamy.onestep.feature_record.presentation

import com.compamy.onestep.feature_tracking.data.data_source.foreground_service.LocationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

sealed class JourneyActions {
    data class ToggleTracking(val value: Boolean) : JourneyActions()
    data class TrackingLoading(val value: Boolean) : JourneyActions()
    data class TrackingStarted(val value: Boolean) : JourneyActions()
    data class SetCurrentId(val id: String?) : JourneyActions()
    data class SetLastingTime(val elapsedTime: StateFlow<Long> ):JourneyActions()
    data class SetLocationFlow(val locationFlow: StateFlow<LocationState?> ):JourneyActions()
}