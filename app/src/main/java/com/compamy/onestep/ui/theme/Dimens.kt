package com.compamy.onestep.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimens(
    val extraSmall:Dp =0.dp,
    val small:Dp =0.dp,
    val medium:Dp =0.dp,
    val large:Dp =0.dp
)

val SmallScreen =Dimens(5.dp,6.dp,35.dp,45.dp)
val MediumScreen =Dimens(8.dp,13.dp,35.dp,65.dp)