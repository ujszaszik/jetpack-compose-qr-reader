package hu.ujszaszik.qrreader.reader.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import hu.ujszaszik.qrreader.reader.ReaderViewModel.BarcodeResult
import hu.ujszaszik.qrreader.resources.Dimens

@Composable
fun QrResultScreen(qrResult: BarcodeResult?) {
    when (qrResult) {
        is BarcodeResult.Success -> QrResultSuccessScreen(qrResult.rawValue)
        is BarcodeResult.Error -> QrResultErrorScreen(qrResult.throwable)
    }
}

@Composable
fun QrResultSuccessScreen(rawValue: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.marginDefault),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) { Text(text = rawValue) }
}

@Composable
fun QrResultErrorScreen(throwable: Throwable) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.marginDefault),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) { Text(text = throwable.message ?: "UNKNOWN ERROR") }
}