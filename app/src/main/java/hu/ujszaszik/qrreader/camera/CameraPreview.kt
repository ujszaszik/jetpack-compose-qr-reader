package hu.ujszaszik.qrreader.camera

import android.widget.LinearLayout
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import hu.ujszaszik.qrreader.reader.ReaderViewModel

@Composable
fun CameraPreview(viewModel: ReaderViewModel) {
    val lifecycleOwner = LocalLifecycleOwner.current
    AndroidView(
        factory = { context ->
            PreviewView(context).apply {
                setSizeToMatchParent()
                scaleType = PreviewView.ScaleType.FILL_CENTER
                implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                CameraPreviewGenerator(context, lifecycleOwner)
                    .doOnDetection { viewModel.onBarcodeDetected(it) }
                    .doOnDetectionError { viewModel.onBarcodeError(it) }
                    .setupOnView(this)
            }
        })
}

private fun PreviewView.setSizeToMatchParent() {
    layoutParams =
        LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
}