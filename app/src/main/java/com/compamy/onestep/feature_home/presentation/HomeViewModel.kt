package com.compamy.onestep.feature_home.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.compamy.onestep.feature_home.domain.model.TheJourney
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel  @Inject constructor(private val initCount:Int):ViewModel() {

    private var _count = mutableStateOf(0)
    var count :State<Int> =_count

    init {
        _count.value =initCount
    }
    var journeyItems = mutableStateListOf<TheJourney>().also {
        for(i in 1..10)
        {
            it.add(TheJourney("${i}","title ${i}","desc",100000L,1000, listOf()))
        }
    }

}