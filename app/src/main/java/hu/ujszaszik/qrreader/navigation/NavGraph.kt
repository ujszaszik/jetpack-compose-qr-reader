package hu.ujszaszik.qrreader.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import hu.ujszaszik.qrreader.MainViewModel
import hu.ujszaszik.qrreader.MainViewModel.PermissionAction.Granted
import hu.ujszaszik.qrreader.extension.getParcelable
import hu.ujszaszik.qrreader.menu.MenuScreen
import hu.ujszaszik.qrreader.reader.ReaderViewModel.BarcodeResult
import hu.ujszaszik.qrreader.reader.ui.QrReaderScreen
import hu.ujszaszik.qrreader.reader.ui.QrResultScreen
import hu.ujszaszik.qrreader.resources.Strings

@Composable
fun NavGraph(navController: NavHostController, viewModel: MainViewModel) {
    val action = viewModel.permissionAction.observeAsState()
    action.value?.let { if (it == Granted) navController.navigateToQrReader() }

    NavHost(navController, startDestination = Directions.Menu) {
        composable(Directions.Menu) {
            MenuScreen(viewModel)
        }
        composable(Directions.QrReader) {
            QrReaderScreen(navController)
        }
        composable(Directions.QrResult) {
            val result = navController.getParcelable<BarcodeResult>(Strings.qrCodeResultKey)
            QrResultScreen(result)
        }
    }
}