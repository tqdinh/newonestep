package com.compamy.onestep.feature_home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.compamy.onestep.R
import com.compamy.onestep.feature_home.components.journeyCard
import com.compamy.onestep.util.Screen

@Composable
fun HomeScreen(
    navController: NavController,
    testingCount: Int,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = HomeViewModelFactory(
            testingCount
        )
    )
) {
    val journeyItems = viewModel.journeyItems

    val count by remember { mutableStateOf(0) }
    ConstraintLayout(
        Modifier.fillMaxSize()
    ) {
        val (search, list) = createRefs()
        Box(modifier = Modifier.constrainAs(search) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)

        }) {
            searchBar()
        }
        Box(modifier = Modifier.constrainAs(list) {
            top.linkTo(search.bottom)
            start.linkTo(search.start)

        }) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(journeyItems) {
                    journeyCard("", "Dinh", "100", "title", "desc", emptyList(), onClick = {
                        navController.navigate(Screen.JourneyDetailScreen.route){

                        }
                    })

                }

            }
        }

    }


}

@Preview
@Composable
fun previewHome() {
    HomeScreen(navController = rememberNavController(), 123456)
}

@Preview
@Composable
fun searchBar() {
    TopAppBar(title = {
        Text(text = "Home", style = MaterialTheme.typography.bodySmall)
    }, actions = {
        IconButton(onClick = { /* Handle search click */ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search), // Use a suitable search icon
                contentDescription = "Search"
            )
        }
    }, backgroundColor = Color.White
    )
}
