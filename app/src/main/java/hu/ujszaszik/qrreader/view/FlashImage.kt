package hu.ujszaszik.qrreader.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlashOff
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import hu.ujszaszik.qrreader.camera.CameraHolder
import hu.ujszaszik.qrreader.resources.Dimens
import hu.ujszaszik.qrreader.resources.Strings

@Composable
fun FlashImage() {
    var isTurnedOn by remember { mutableStateOf(false) }

    Icon(
        if (isTurnedOn) Icons.Default.FlashOff else Icons.Default.FlashOn,
        tint = Color.White,
        contentDescription = Strings.empty,
        modifier = Modifier
            .clickable {
                CameraHolder.camera?.cameraControl?.enableTorch(!isTurnedOn)
                isTurnedOn = !isTurnedOn
            }
            .size(Dimens.cancelImageSize)
    )
}