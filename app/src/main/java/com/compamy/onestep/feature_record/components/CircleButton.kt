package com.compamy.onestep.feature_record.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun CircleButton(onClick:  () -> Unit) {
    Button(
        onClick = onClick, modifier = Modifier
            .size(80.dp)
            .clip(CircleShape),
        colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = Color.Blue)
    ) {
        Text("Start")
    }
}