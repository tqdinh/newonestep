import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.compamy.onestep.feature_take_photo.utils.CameraPreviewScreen

@Composable
fun CameraScreen(navController: NavController, placeId:String,journeyId:String)
{
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        CameraPreviewScreen()
    }
}


@Preview
@Composable
fun previewTakePhoto()
{
    CameraScreen(rememberNavController(),"33333","444")
}
