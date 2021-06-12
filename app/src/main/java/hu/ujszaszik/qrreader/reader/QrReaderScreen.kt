package hu.ujszaszik.qrreader.reader

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import hu.ujszaszik.qrreader.camera.CameraPreview
import hu.ujszaszik.qrreader.navigation.navigateToMenu
import hu.ujszaszik.qrreader.resources.Dimens
import hu.ujszaszik.qrreader.view.CancelImage

@Composable
fun QrReaderScreen(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        CameraPreview()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.marginDefault),
            horizontalArrangement = Arrangement.End
        ) { CancelImage(onClick = { navController.navigateToMenu() }) }
    }
}