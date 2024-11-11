package com.compamy.onestep.feature_record.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Divider
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun bottomSheet() {
    val sheetHeight = LocalConfiguration.current.screenHeightDp.dp * 0.5f
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberBottomSheetScaffoldState(bottomSheetState = rememberStandardBottomSheetState(initialValue = SheetValue.PartiallyExpanded))

    BottomSheetScaffold(
        scaffoldState = bottomSheetState,
        sheetPeekHeight = sheetHeight,
        sheetContent = { bottomSheetContent() }) {

    }
}

@Preview
@Composable
fun previewBottomSheet() {
    bottomSheet()
}

@Composable
fun bottomSheetContent() {
    Column(
        modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "Long An trip", style = MaterialTheme.typography.headlineLarge)
            Text("Tan tru hang cau vua,LongAn,Vietname", color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Divider(color = Color.Gray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .padding(10.dp)
                .height(50.dp)
                .fillMaxWidth()
        ) {


            ConstraintLayout(
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Gray)

            ) {

                val (text0, text1) = createRefs()
                Text(text = "1.4Mi", modifier = Modifier.constrainAs(text0) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }, style = MaterialTheme.typography.headlineMedium, color = Color.Black)

                Text(text = "Route length", modifier = Modifier.constrainAs(text1) {
                    start.linkTo(text0.start)
                    top.linkTo(text0.bottom)

                })

            }

            ConstraintLayout(
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Green)
            ) {
                val (text0, text1) = createRefs()

                Text(text = "30 cp", modifier = Modifier.constrainAs(text0) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }, style = MaterialTheme.typography.headlineMedium, color = Color.Black)

                Text(text = "No. Checkount ", modifier = Modifier.constrainAs(text1) {
                    start.linkTo(text0.start)
                    top.linkTo(text0.bottom)

                })

            }


        }


        Text(text = "This route will take you to the Pulau Perhentian Windmill area. There are beautiful sea views... more")



    }

}

@Preview
@Composable
fun previewBottomSheetContent() {
    bottomSheetContent()

}
