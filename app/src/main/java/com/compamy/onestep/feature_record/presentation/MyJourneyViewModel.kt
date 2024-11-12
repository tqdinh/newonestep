package com.compamy.onestep.feature_record.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyJourneyViewModel @Inject constructor(private val tracked:Boolean) :ViewModel() {
    private val _isOnTracking : MutableState<Boolean>  = mutableStateOf(tracked)
    val isOnTracking : State<Boolean> =_isOnTracking

    fun onAction(action:JourneyActions)
    {
        when(action){
            is JourneyActions.ToggleTracking -> {
                _isOnTracking.value =action.value
            }

            is JourneyActions.TrackingLoading -> TODO()
            is JourneyActions.TrackingStarted -> TODO()
        }
    }



}


 class MyJourneyViewModelFactory (private val tracking:Boolean) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MyJourneyViewModel::class.java))
        {
            return MyJourneyViewModel(tracking) as T
        }
        throw IllegalArgumentException("not found")
    }

}


