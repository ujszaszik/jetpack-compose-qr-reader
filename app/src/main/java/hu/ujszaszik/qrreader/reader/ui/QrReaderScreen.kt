package hu.ujszaszik.qrreader.reader.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import hu.ujszaszik.qrreader.camera.CameraPreview
import hu.ujszaszik.qrreader.navigation.navigateToMenu
import hu.ujszaszik.qrreader.navigation.navigateToQrResult
import hu.ujszaszik.qrreader.reader.ReaderViewModel
import hu.ujszaszik.qrreader.resources.Dimens
import hu.ujszaszik.qrreader.view.CancelImage
import hu.ujszaszik.qrreader.view.FlashImage

@Composable
fun QrReaderScreen(navController: NavHostController, viewModel: ReaderViewModel = viewModel()) {
    val result = viewModel.barcodeResult.observeAsState()
    result.value?.let { navController.navigateToQrResult(it) }

    Box(modifier = Modifier.fillMaxSize()) {
        CameraPreview(viewModel)
        QrTargetSquare(
            color = Dimens.targetSquareColor,
            sizePercentPortrait = Dimens.targetSquareSizePercentPortrait,
            sizePercentLandscape = Dimens.targetSquareSizePercentLandscape,
            strokeWidth = Dimens.targetSquareStrokeWidth,
            cornerRadius = Dimens.targetSquareCornerRadius,
            cornerLength = Dimens.targetSquareCornerLength,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.marginDefault),
            horizontalArrangement = Arrangement.Start
        ) { FlashImage() }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.marginDefault),
            horizontalArrangement = Arrangement.End
        ) { CancelImage(onClick = { navController.navigateToMenu() }) }
    }
}