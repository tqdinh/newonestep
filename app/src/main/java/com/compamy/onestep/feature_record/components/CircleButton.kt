package com.compamy.onestep.feature_record.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compamy.onestep.R


@Composable
fun CircleButton(text:String="start",onClick:  () -> Unit) {
    Button(
        onClick = onClick, modifier = Modifier
            .size(80.dp)
            .clip(CircleShape),
        colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = Color.Blue)
    ) {
        Text(text)
    }
}

@Composable
fun TakePhotoButton(onClick:  () -> Unit) {
    Button(
        onClick = onClick, modifier = Modifier
            .size(80.dp)
            .clip(CircleShape),
        colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = Color.Gray)
    ) {
        Image(painter = painterResource(R.drawable.ic_camera), contentDescription = "camera")
    }
}

@Composable
fun StopTrackingButton(onClick:  () -> Unit) {
    Button(
        onClick = onClick, modifier = Modifier
            .size(80.dp)
            .clip(CircleShape),
        colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = Color.White)
    ) {
        Image(painter = painterResource(R.drawable.ic_stop_tracking), contentDescription = "camera")
    }
}