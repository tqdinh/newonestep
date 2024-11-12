package com.compamy.onestep.feature_home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ErrorResult
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import com.compamy.onestep.R

@Composable
fun journeyCard(
    url: String,
    name: String,
    date: String,
    title: String,
    desc: String,
    photoList: List<String>,
    onClick:()->Unit
) {
    val lazyImageWidth = LocalConfiguration.current.screenWidthDp.dp * 0.5f
    val lazyImageHeight = LocalConfiguration.current.screenHeightDp.dp * 0.3f
    Card(modifier = Modifier.clickable { onClick.invoke()
    }) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .weight(0.2f)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(40))
                ) {
                    Image(
                        //  painter = rememberAsyncImagePainter("https://via.placeholder.com/150"),
                        painterResource(id = R.drawable.app_nav_account),
                        contentDescription = "avartar",
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .fillMaxHeight()
                            .aspectRatio(1f)
                    )
                }

//                    GlideImage(
////                        model = "https://via.placeholder.com/150",
//                        model = R.drawable.app_nav_map,
//                        contentDescription = "image",
//                        modifier = Modifier
//                            .size(40.dp)
//                            .clip(
//                                RoundedCornerShape(50)
//                            )
//                    )

                Column(
                    Modifier
//                        .background(Color(0xFFffffff))
                        .weight(0.6f)
                ) {
                    Text(text = name, style = MaterialTheme.typography.bodyLarge)
                    Text(
                        date,
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                    )

                }


            }

            Box()
            {
                Column()
                {
                    Text(
                        text = title, style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = desc,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Box()
                    {
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(5) { i ->

                                AsyncImage(
                                    model = "https://cdn.pixabay.com/photo/2024/05/26/10/15/bird-8788491_1280.jpg",

                                    placeholder = painterResource(R.drawable.ic_place_holder),
                                    contentDescription = "photo${i}",
                                    error = painterResource(id = R.drawable.ic_warning),
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .width(lazyImageWidth)
                                        .height(lazyImageHeight)
                                        .fillMaxWidth()
                                        .clip(
                                            RoundedCornerShape(8.dp)
                                        ),
                                )

                            }
                        }

                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(), horizontalArrangement = Arrangement.Start
            )
            {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Share, contentDescription = "icon_share")

                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = "icon_delete")
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "icon_add")
                }

            }


        }


    }

}

@Preview
@Composable
fun previewJourneyCard() {

    journeyCard(
        "url",
        "Dinh Truong",
        "October 23, 2024 at 9:45",
        "Long An",
        "Embark on the adventure of a lifetime! Our exclusive trip package offers you the chance to escape the ordinary and immerse yourself in breathtaking destinations, cultural wonders, and thrilling activities. Imagine exploring hidden gems, tasting",
        listOf(), onClick = {}
    )

}

@Preview
@Composable
fun AsyncImageHere() {

    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data("https://cdn.pixabay.com/photo/2024/05/26/10/15/bird-8788491_1280.jpg")
        .listener(object:ImageRequest.Listener{
            override fun onError(request: ImageRequest, result: ErrorResult) {
                super.onError(request, result)

            }
            override fun onSuccess(request: ImageRequest, result: SuccessResult) {
                super.onSuccess(request, result)
            }
        })
        .build()
    AsyncImage(
        model = imageRequest,
        placeholder = painterResource(R.drawable.ic_place_holder),
        contentDescription = "photo${2}",
        error = painterResource(id = R.drawable.ic_warning),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(40.dp)
            .height(40.dp)
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(8.dp)
            ),
    )

}
