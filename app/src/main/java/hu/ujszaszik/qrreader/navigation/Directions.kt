package hu.ujszaszik.qrreader.navigation

import androidx.navigation.NavHostController

object Directions {
    const val Menu = "menu"
    const val QrReader = "qrReader"
}

fun NavHostController.navigateToMenu() {
    navigate(Directions.Menu)
}

fun NavHostController.navigateToQrReader() {
    navigate(Directions.QrReader)
}