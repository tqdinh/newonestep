package com.compamy.onestep


import CameraScreen
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.compamy.onestep.feature_home.presentation.AccountScreen
import com.compamy.onestep.feature_home.presentation.HomeScreen
import com.compamy.onestep.feature_record.presentation.JourneyScreen
import com.compamy.onestep.feature_record.presentation.MapScreen
import com.compamy.onestep.feature_record.presentation.NotificationScreen
import com.compamy.onestep.feature_record.presentation.RecordScreen
import com.compamy.onestep.ui.theme.OneStepTheme
import com.compamy.onestep.util.Screen

class MainActivity : ComponentActivity() {

    private val cameraPermissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {

            } else {
                // Camera permission denied
            }

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.CAMERA
            ) -> {

            }

            else -> {
                cameraPermissionRequest.launch(android.Manifest.permission.CAMERA)
            }
        }

        enableEdgeToEdge()
        setContent {
            mainScreen()
        }
    }
}


@Composable
fun bottomNavigation(navController: NavHostController) {
    val currentRoute = navController.currentDestination?.route


    BottomNavigation(
        backgroundColor = Color.White,
    ) {
        BottomNavigationItem(
            selected = currentRoute == Screen.HomeScreen.route,
            onClick = { navController.navigate(Screen.HomeScreen.route) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.app_nav_home),
                    contentDescription = Screen.HomeScreen.route
                )
            },
//            label = { Text(Screen.HomeScreen.route) }
        )

        BottomNavigationItem(
            selected = currentRoute == Screen.MapScreen.route,
            onClick = { navController.navigate(Screen.MapScreen.route) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.app_nav_map),
                    contentDescription = Screen.MapScreen.route
                )
            },
//            label = { Text(Screen.MapScreen.route) }
        )

        BottomNavigationItem(
            selected = currentRoute == Screen.RecordScreen.route,
            onClick = { navController.navigate(Screen.RecordScreen.route) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.app_nav_record),
                    contentDescription = Screen.RecordScreen.route
                )
            },
//            label = { Text(Screen.RecordScreen.route) }
        )

        BottomNavigationItem(
            selected = currentRoute == Screen.NotificationScreen.route,
            onClick = { navController.navigate(Screen.NotificationScreen.route) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.app_nav_notification),
                    contentDescription = Screen.NotificationScreen.route
                )
            },
//            label = { Text(Screen.NotificationScreen.route) }
        )


        BottomNavigationItem(
            selected = false, onClick = { navController.navigate(Screen.AccountScreen.route) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.app_nav_account),
                    contentDescription = Screen.AccountScreen.route
                )
            },
//            label = { Text(Screen.AccountScreen.route) }
        )

    }
}

@Preview
@Composable
fun mainScreen() {
    OneStepTheme {
        val navController = rememberNavController()

        val navBackStackEntry = navController.currentBackStackEntryAsState()

        val currentRoute = navBackStackEntry.value?.destination?.route

        Scaffold(modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
            bottomBar = {
                if(currentRoute == Screen.HomeScreen.route
                    || currentRoute == Screen.MapScreen.route
                    ||currentRoute == Screen.RecordScreen.route
                    || currentRoute == Screen.NotificationScreen .route
                    ||currentRoute == Screen.AccountScreen.route
                    )

                bottomNavigation(navController)
            }) { padding ->

            NavHost(
                modifier = Modifier.padding(padding),
                navController = navController,
                startDestination = "main_grapth"
            ) {

                navigation(startDestination = Screen.HomeScreen.route,route="main_grapth")
                {
                    composable(route = Screen.HomeScreen.route) {
                        HomeScreen(navController = navController, 123)
                    }

                    composable(route = Screen.JourneyDetailScreen.route) {
                        JourneyScreen(navController = navController)
                    }

                }



                composable(route = Screen.MapScreen.route) {
                    MapScreen(navController = navController)
                }
                composable(route = Screen.RecordScreen.route) {
                    RecordScreen(navController = navController)
                }
                composable(route = Screen.NotificationScreen.route) {
                    NotificationScreen(navController = navController)
                }
                composable(route = Screen.AccountScreen.route) {
                    AccountScreen(navController = navController)
                }
                composable(
                    route = Screen.CameraScreen.route + "?journeyId={journeyId}&placeId={placeId}",
                    arguments = listOf(navArgument(name = "journeyId") {
                        type = NavType.StringType
                        defaultValue = ""

                    }, navArgument(name = "placeId") {
                        type = NavType.StringType
                        defaultValue = ""

                    })
                ) { backStackEntry ->
                    val jid = backStackEntry.arguments?.getString("journeyId") ?: ""
                    val pid = backStackEntry.arguments?.getString("placeId") ?: ""

                    CameraScreen(navController = navController, jid, pid)
                }

            }
        }
    }
}

