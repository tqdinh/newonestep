package com.compamy.onestep.feature_home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeViewModelFactory(private val initState:Int) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if( modelClass.isAssignableFrom(HomeViewModel::class.java))
            return HomeViewModel(initState) as T
        throw IllegalArgumentException("unknow class")
    }
}