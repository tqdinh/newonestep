package com.compamy.onestep.feature_record.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compamy.onestep.R

@Preview
@Composable
fun ScrollableBottomSheetWithCarousel() {
    // Fake image list for carousel
    val images = listOf(
        painterResource(id = R.drawable.sea), // Replace with your actual images
        painterResource(id = R.drawable.jungle),
        painterResource(id = R.drawable.valley)
    )

    // Outer Box to layer elements
    Box(modifier = Modifier.fillMaxSize()) {
        // Scrollable content with a LazyColumn
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Carousel at the top
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            ) {
                items(images.size) { i ->
                    Image(
                        painter = images[i],
                        contentDescription = "Carousel Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(300.dp)
                            .padding(4.dp)
                    )
                }
            }

            // Scrollable Bottom Sheet content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Title and Location
                Text(text = "Pulau Perhentian Windmill", style = MaterialTheme.typography.titleLarge)
                Text(text = "Moderate · 4.4 (67) · Kuala Besut, Trengganu, MY", color = Color.Gray)

                Spacer(modifier = Modifier.height(16.dp))

                // Trail Information
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("1.4 mi", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text("Trail length", color = Color.Gray)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("413 ft", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text("Elevation gain", color = Color.Gray)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("50 min", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text("Average time", color = Color.Gray)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Description
                Text(
                    text = "This route will take you to the Pulau Perhentian Windmill area. There are beautiful sea views...",
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Map Preview Section
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(Color.LightGray)
                        .padding(8.dp)
                ) {
                    Text(
                        text = "Preview",
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                Spacer(modifier = Modifier.height(80.dp)) // Space for the bottom buttons
            }
        }

        // Bottom Buttons pinned at the bottom
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { /* Handle download action */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32)), // Dark green color for the Download button
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .padding(end = 8.dp)
            ) {
                Text("Download", color = Color.White)
            }
            OutlinedButton(
                onClick = { /* Handle navigate action */ },
                colors = ButtonDefaults.outlinedButtonColors(),
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .padding(start = 8.dp)
            ) {
                Text("Navigate")
            }
        }
    }
}