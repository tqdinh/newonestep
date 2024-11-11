package com.compamy.onestep.feature_record.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.compamy.onestep.util.Screen

@Composable
fun RecordScreen (navController: NavController){
    Column {

        Button(onClick = { navController.navigate(Screen.CameraScreen.route+"?journeyId=jjjjjj&placeId=ppppp") }) {
            Text(text = "Take photo")
        }

    }

}