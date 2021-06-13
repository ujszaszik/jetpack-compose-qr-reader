package hu.ujszaszik.qrreader.camera

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import hu.ujszaszik.qrreader.analyzer.BarcodeAnalyzer
import hu.ujszaszik.qrreader.analyzer.BarcodeErrorListener
import hu.ujszaszik.qrreader.analyzer.BarcodeListener
import java.util.concurrent.Executor

class CameraPreviewGenerator(
    private val context: Context,
    private val lifecycleOwner: LifecycleOwner
) {

    private lateinit var barcodeListener: BarcodeListener
    private lateinit var barcodeErrorListener: BarcodeErrorListener

    fun setupOnView(previewView: PreviewView) {
        val executor = ContextCompat.getMainExecutor(context)
        ProcessCameraProvider.getInstance(context).run {
            addListener({
                val preview = createPreview(previewView)
                val imageAnalyzer = createImageAnalysis(executor)
                get().bindSafely(lifecycleOwner, preview, imageAnalyzer)
            }, executor)
        }
    }

    fun doOnDetection(block: (String) -> Unit): CameraPreviewGenerator {
        return apply { barcodeListener = BarcodeListener(block) }
    }

    fun doOnDetectionError(block: (Throwable) -> Unit): CameraPreviewGenerator {
        return apply { barcodeErrorListener = BarcodeErrorListener(block) }
    }

    private fun createPreview(previewView: PreviewView): Preview {
        return Preview.Builder().build().apply { setSurfaceProvider(previewView.surfaceProvider) }
    }

    private fun createImageAnalysis(cameraExecutor: Executor): ImageAnalysis {
        val barcodeAnalyzer = BarcodeAnalyzer(barcodeListener, barcodeErrorListener)
        return ImageAnalysis.Builder().build()
            .also { it.setAnalyzer(cameraExecutor, barcodeAnalyzer) }
    }

    private fun ProcessCameraProvider.bindSafely(
        lifecycleOwner: LifecycleOwner,
        vararg useCases: UseCase
    ) {
        unbindAll()
        bindToLifecycle(lifecycleOwner, CameraSelector.DEFAULT_BACK_CAMERA, *useCases)
    }
}