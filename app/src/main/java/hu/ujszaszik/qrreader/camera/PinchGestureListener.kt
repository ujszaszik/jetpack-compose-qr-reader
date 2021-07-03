package hu.ujszaszik.qrreader.camera

import android.view.ScaleGestureDetector
import androidx.camera.core.Camera

class PinchGestureListener(
    private val camera: Camera,
) : ScaleGestureDetector.SimpleOnScaleGestureListener() {

    override fun onScale(detector: ScaleGestureDetector): Boolean {
        val currentZoomRatio = camera.cameraInfo.zoomState.value?.zoomRatio ?: 0F
        val delta = detector.scaleFactor
        camera.cameraControl.setZoomRatio(currentZoomRatio * delta)
        return true
    }
}