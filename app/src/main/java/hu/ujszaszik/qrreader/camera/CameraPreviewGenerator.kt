package hu.ujszaszik.qrreader.camera

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner

class CameraPreviewGenerator(
    private val context: Context,
    private val lifecycleOwner: LifecycleOwner
) {

    fun setupOnView(previewView: PreviewView) {
        ProcessCameraProvider.getInstance(context).run {
            addListener({
                val preview = createPreview(previewView)
                get().bindSafely(lifecycleOwner, preview)
            }, ContextCompat.getMainExecutor(context))
        }
    }

    private fun createPreview(previewView: PreviewView): Preview {
        return Preview.Builder().build().apply { setSurfaceProvider(previewView.surfaceProvider) }
    }

    private fun ProcessCameraProvider.bindSafely(lifecycleOwner: LifecycleOwner, preview: Preview) {
        unbindAll()
        bindToLifecycle(lifecycleOwner, CameraSelector.DEFAULT_BACK_CAMERA, preview)
    }
}