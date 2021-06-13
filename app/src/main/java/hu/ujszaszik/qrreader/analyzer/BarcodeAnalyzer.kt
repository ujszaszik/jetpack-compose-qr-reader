package hu.ujszaszik.qrreader.analyzer

import android.annotation.SuppressLint
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.Barcode
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

class BarcodeAnalyzer(
    private val doOnSuccessListener: BarcodeListener,
    private val doOnErrorListener: BarcodeErrorListener
) : ImageAnalysis.Analyzer {

    private val scanner = BarcodeScanning.getClient()

    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
            scanner.process(image)
                .addOnSuccessListener { barcodes ->
                    barcodes.forEach { it.doOnDetected(doOnSuccessListener) }
                }
                .addOnFailureListener { doOnErrorListener.onError(it) }
                .addOnCompleteListener { imageProxy.close() }
        }
    }

    private fun Barcode.doOnDetected(listener: BarcodeListener) {
        rawValue?.let { listener.onDetected(it) }
    }
}