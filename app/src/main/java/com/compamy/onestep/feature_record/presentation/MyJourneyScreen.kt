package com.compamy.onestep.feature_record.presentation

import android.app.ActivityManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.compamy.onestep.R
import com.compamy.onestep.feature_record.components.CircleButton
import com.compamy.onestep.feature_record.components.StopTrackingButton
import com.compamy.onestep.feature_record.components.TakePhotoButton
import com.compamy.onestep.feature_tracking.data.data_source.foreground_service.LocationTrackingService
import com.compamy.onestep.util.Screen

@Composable
fun MyJourneyScreen(
    navController: NavController,
    viewModel: MyJourneyViewModel = viewModel(factory = MyJourneyViewModelFactory(false)),
    images: List<Int> = listOf(R.drawable.sea, R.drawable.jungle, R.drawable.valley)
) {
    var boundService by remember {
        mutableStateOf<LocationTrackingService?>(null)
    }
    val context = LocalContext.current


    val connection = remember {
        object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                boundService = (service as LocationTrackingService.TrackingBinder).getService()
                boundService?.currentJourneyId?.let {
                    viewModel.onAction(JourneyActions.SetCurrentId(it))
                }
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                boundService = null
                viewModel.onAction(JourneyActions.SetCurrentId(null))
            }

        }
    }
    LaunchedEffect(true) {
        if (isMyServiceRunnning(context, LocationTrackingService::class.java)) {
            val intent = Intent(context, LocationTrackingService::class.java)
            context.bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }
    val currentTrackingJourneyId :String? =viewModel.currentJourney?.value

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Column {
            Box(
                modifier = Modifier
                    .background(Color.Gray)
                    .weight(8f)
                    .fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .background(Color.Yellow)
                    .weight(2f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

                if (null != currentTrackingJourneyId) Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CircleButton(currentTrackingJourneyId) {  }
                    TakePhotoButton {
                        navController.navigate(Screen.CameraScreen.route)
                        {
                            popUpTo(Screen.RecordScreen.route) { inclusive = true }
                        }
                    }
                    StopTrackingButton {
                        viewModel.onAction(JourneyActions.SetCurrentId(null))
                        boundService?.stopForeground()
                        context.unbindService(connection)
                    }

                    Box(
                        modifier = Modifier
                            .background(Color.Gray)
                            .align(Alignment.CenterVertically)
                    )
                    {
                        LazyRow() {
                            items(images) { img ->
                                Image(
                                    painter = painterResource(img),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .width(20.dp)
                                        .height(20.dp)
                                )
                            }
                        }
                    }

                }
                else CircleButton {
                    startAndBindService(context,connection)
                }


            }

        }
    }
}

@Preview
@Composable
fun previewRecordScreen() {
    MyJourneyScreen(rememberNavController())
}


@Composable
fun RecordScreenLandScape(
    navController: NavController,
    images: List<Int> = listOf(R.drawable.sea, R.drawable.jungle, R.drawable.valley)
) {

    val screenHeightSize = LocalConfiguration.current.screenHeightDp.dp
    val screenWidthSize = LocalConfiguration.current.screenWidthDp.dp

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Row {
            Box(
                modifier = Modifier
                    .background(Color.Gray)
                    .weight(8f)
                    .fillMaxHeight()
            )
            Box(
                modifier = Modifier
                    .background(Color.Yellow)
                    .weight(2f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CircleButton { }
                    CircleButton { }
                    CircleButton { }

                }

            }

        }
    }
}


@Composable
fun onTrackingLandScape(
    navController: NavController,
    images: List<Int> = listOf(R.drawable.sea, R.drawable.jungle, R.drawable.valley)
) {

    val screenHeightSize = LocalConfiguration.current.screenHeightDp.dp
    val screenWidthSize = LocalConfiguration.current.screenWidthDp.dp
    val trackingItems: List<Int> = listOf(R.drawable.sea, R.drawable.jungle, R.drawable.valley)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Row {
            Box(
                modifier = Modifier
                    .background(Color.Gray)
                    .weight(8f)
                    .fillMaxHeight()
            )
            Box(
                modifier = Modifier
                    .background(Color.Yellow)
                    .weight(2f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {

                ConstraintLayout(
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Cyan)
                ) {

                    val (button, imgRow) = createRefs()

                    Row(modifier = Modifier
                        .constrainAs(button) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)

                        }
                        .fillMaxWidth()
                        .background(Color.Cyan),
                        horizontalArrangement = Arrangement.SpaceEvenly) {
                        CircleButton { }
                        CircleButton { }
                    }


                    LazyRow(modifier = Modifier.constrainAs(imgRow) {
                        start.linkTo(parent.start)
                        top.linkTo(button.bottom)
                    }) {
                        items(trackingItems) { item ->
                            Image(
                                modifier = Modifier
                                    .width(40.dp)
                                    .height(40.dp),
                                painter = painterResource(item),
                                contentDescription = ""
                            )

                        }
                    }


                }

            }

        }
    }
}

@Preview(
    showSystemUi = true,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
@Composable
fun previewRecordScreenLandScape() {
    RecordScreenLandScape(rememberNavController())
}


@Composable
fun onTracking(
    navController: NavController,
    viewModel: MyJourneyViewModel = viewModel(factory = MyJourneyViewModelFactory(false)),

    images: List<Int> = listOf(R.drawable.sea, R.drawable.jungle, R.drawable.valley)
) {
    val screenHeightSize = LocalConfiguration.current.screenHeightDp.dp
    val screenWidthSize = LocalConfiguration.current.screenWidthDp.dp


    var boundService by remember {
        mutableStateOf<LocationTrackingService?>(null)
    }

    val connection = remember {
        object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                boundService = (service as LocationTrackingService.TrackingBinder).getService()

            }

            override fun onServiceDisconnected(name: ComponentName?) {
                boundService = null
            }

        }
    }


    val isOnTrack: Boolean = viewModel.isOnTracking.value

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Column {
            Box(
                modifier = Modifier
                    .background(Color.Gray)
                    .weight(8f)
                    .fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .background(Color.Yellow)
                    .weight(2f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TakePhotoButton { }
                    StopTrackingButton {

                        //bindService(context,connection)
                      //  viewModel.onAction(JourneyActions.ToggleTracking(!isOnTrack))
                    }

                    Box(
                        modifier = Modifier
                            .background(Color.Gray)
                            .align(Alignment.CenterVertically)
                    )
                    {
                        LazyRow() {
                            items(images) { img ->
                                Image(
                                    painter = painterResource(img),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .width(20.dp)
                                        .height(20.dp)
                                )
                            }
                        }
                    }


                }
            }

        }
    }

}

@Preview
@Composable
fun previewOnTracking() {
    onTracking(rememberNavController())
}

@Preview(
    showSystemUi = true,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
@Composable
fun previewOnTrackingLandScape() {
    onTrackingLandScape(rememberNavController())
}



fun startAndBindService(context: Context, connection: ServiceConnection) {
    val intent = Intent(context, LocationTrackingService::class.java)
    context.startService(intent)
    context.bindService(intent, connection, Context.BIND_AUTO_CREATE)

}


private fun isMyServiceRunnning(context: Context, serviceClass: Class<*>): Boolean {
    val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    return manager.getRunningServices(Int.MAX_VALUE).any {
        it.service.className == serviceClass.name
    }
}



