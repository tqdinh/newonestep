package com.compamy.onestep.ForTesting

import android.content.Context
import com.compamy.onestep.R

class ClassUnderTest constructor(val context:Context) {
    fun getAppName():String
    {
        return context.getString(R.string.app_name)
    }
}