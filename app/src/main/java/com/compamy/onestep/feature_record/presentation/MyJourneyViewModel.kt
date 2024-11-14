package com.compamy.onestep.feature_record.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.compamy.onestep.feature_tracking.data.data_source.foreground_service.LocationState
import com.compamy.onestep.feature_tracking.data.data_source.foreground_service.LocationTrackingService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyJourneyViewModel @Inject constructor(private val tracked: Boolean) : ViewModel() {
    private val _isOnTracking: MutableState<Boolean> = mutableStateOf(tracked)
    val isOnTracking: State<Boolean> = _isOnTracking

    private val _currentJourney: MutableState<String?> = mutableStateOf(null)
    val currentJourney: State<String?> = _currentJourney

    private val _elapsedFlow = MutableStateFlow(0L)
    val elapsedFlow: StateFlow<Long> = _elapsedFlow

    private val _currentLocationFlow: MutableStateFlow<LocationState?> = MutableStateFlow(null)
    val currentLocationState: StateFlow<LocationState?> = _currentLocationFlow

    fun onAction(action: JourneyActions) {
        when (action) {
            is JourneyActions.ToggleTracking -> {
                _isOnTracking.value = action.value
            }

            is JourneyActions.TrackingLoading -> TODO()
            is JourneyActions.TrackingStarted -> TODO()
            is JourneyActions.SetCurrentId -> {
                _currentJourney.value = action.id
            }

            is JourneyActions.SetLastingTime -> {
                collectStateFromService(action.elapsedTime)
            }

            is JourneyActions.SetLocationFlow ->{
                collectLocationState(action.locationFlow)
            }
        }
    }

    fun collectStateFromService(serviceTime: StateFlow<Long>) {
        viewModelScope.launch(Dispatchers.IO) {
            serviceTime.collect { time ->
                _elapsedFlow.value = time

            }
        }
    }

    fun collectLocationState(locationState: StateFlow<LocationState?>) {
        viewModelScope.launch(Dispatchers.IO + Job()) {
            locationState.collect { locState ->
                _currentLocationFlow.value = locState
            }

        }
    }
}


class MyJourneyViewModelFactory(private val tracking: Boolean) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyJourneyViewModel::class.java)) {
            return MyJourneyViewModel(tracking) as T
        }
        throw IllegalArgumentException("not found")
    }

}


