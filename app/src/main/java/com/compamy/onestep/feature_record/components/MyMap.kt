package com.compamy.onestep.feature_record.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.compamy.onestep.R
import com.compamy.onestep.util.DevicePreviews
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import com.mapbox.maps.extension.compose.annotation.rememberIconImage

@DevicePreviews
@Composable
fun myMap(initPoint:Point ) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .background(Color.Yellow)
            .fillMaxSize()
    ) {
        MapboxMap(modifier = Modifier.fillMaxSize(), mapViewportState = rememberMapViewportState {
            setCameraOptions {
                zoom(20.0)
                center(initPoint)
                pitch(0.0)
                bearing(0.0)
            }
        })
        {
            val marker = rememberIconImage(key = R.drawable.red_marker, painter = painterResource(R.drawable.red_marker))
            PointAnnotation(point = Point.fromLngLat(106.7135837,10.7780544) ){
                iconImage = marker
            }
        }

    }
}
