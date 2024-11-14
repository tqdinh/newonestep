package com.compamy.onestep.util


object  TimeConvert {



    fun convertMillisToReadableTime(second: Long): String {
        val seconds = second  % 60
        val minutes = second /  60 % 60
        val hours = (second /  ( 60 * 60)) % 24

        return buildString {
            if (hours > 0) append("${hours}h ")
            if (minutes > 0) append("${minutes}m ")
            append("${seconds}s")
        }.trim()
    }

}