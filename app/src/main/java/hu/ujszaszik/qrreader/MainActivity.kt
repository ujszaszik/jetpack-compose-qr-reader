package hu.ujszaszik.qrreader

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import hu.ujszaszik.qrreader.MainViewModel.*
import hu.ujszaszik.qrreader.MainViewModel.PermissionAction.*
import hu.ujszaszik.qrreader.navigation.NavGraph

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observePermissionAction()
        setContent {
            val navController = rememberNavController()
            NavGraph(navController, viewModel)
        }
    }

    private fun observePermissionAction() {
        viewModel.permissionAction.observe(this) { action ->
            action?.let {
                if (it == CheckIfAlreadyGranted) {
                    if (isPermissionAlreadyGranted()) viewModel.onPermissionAction(Granted)
                    else requestCameraPermission()
                }
            }
        }
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this@MainActivity,
            arrayOf(Manifest.permission.CAMERA),
            MY_CAMERA_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_CAMERA_REQUEST_CODE) {
            val isGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED
            if (isGranted) viewModel.onPermissionAction(Granted)
        }
    }

    private fun isPermissionAlreadyGranted(): Boolean {
        return checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        private const val MY_CAMERA_REQUEST_CODE = 100
    }
}