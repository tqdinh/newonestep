package com.compamy.onestep.feature_home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.compamy.onestep.feature_home.components.journeyCard

@Composable
fun HomeScreen(
    navController: NavController,
    testingCount :Int,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = HomeViewModelFactory(testingCount))
) {
    val journeyItems = viewModel.journeyItems

    val count by remember { mutableStateOf(0) }



    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        , verticalArrangement = Arrangement.spacedBy(5.dp)
    )
    {
        items(journeyItems) {
            journeyCard("","Dinh","100","title","desc", emptyList())

        }

    }


}

@Preview
@Composable
fun previewHome() {
    HomeScreen(navController = rememberNavController(),123456)
}