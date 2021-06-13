package hu.ujszaszik.qrreader.navigation

import androidx.navigation.NavHostController
import hu.ujszaszik.qrreader.reader.ReaderViewModel
import hu.ujszaszik.qrreader.resources.Strings

object Directions {
    const val Menu = "menu"
    const val QrReader = "qrReader"
    const val QrResult = "qrResult/{qrResult}"
}

fun NavHostController.navigateToMenu() {
    navigate(Directions.Menu)
}

fun NavHostController.navigateToQrReader() {
    navigate(Directions.QrReader)
}

fun NavHostController.navigateToQrResult(result: ReaderViewModel.BarcodeResult) {
    currentBackStackEntry?.arguments?.putParcelable(Strings.qrCodeResultKey, result)
    navigate(Directions.QrResult)
}