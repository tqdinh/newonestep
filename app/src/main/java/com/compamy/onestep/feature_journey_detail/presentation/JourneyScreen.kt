package com.compamy.onestep.feature_record.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.compamy.onestep.R
import com.compamy.onestep.feature_home.data.data_source.entity.JourneyWithPlaces
import com.compamy.onestep.feature_record.components.bottomSheet
import com.compamy.onestep.feature_record.components.journeyBottomSheet

@Composable
fun JourneyScreen(
    navController: NavController,
    images: List<Int> = listOf(R.drawable.sea)
) {

    val screenHeightSize = LocalConfiguration.current.screenHeightDp.dp
    val screenWidthSize = LocalConfiguration.current.screenWidthDp.dp

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            items(images.size) { i ->
                Image(
                    painter = painterResource(images[i]),
                    contentDescription = "Carousel Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(screenHeightSize * 0.5f)
                        .width(screenWidthSize * 0.7f)
                        .padding(4.dp)
                )
            }
        }
        journeyBottomSheet()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(Color.White)

            , horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {}) {
                Text("Download")
            }
            OutlinedButton(onClick = {}) {
                Text("Navigate")
            }
        }

    }
}

@Preview
@Composable
fun PreviewJourneyScreen() {
    JourneyScreen(rememberNavController())
}