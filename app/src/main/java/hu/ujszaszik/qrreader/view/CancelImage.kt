package hu.ujszaszik.qrreader.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import hu.ujszaszik.qrreader.R
import hu.ujszaszik.qrreader.resources.Dimens
import hu.ujszaszik.qrreader.resources.Strings

@Composable
fun CancelImage(onClick: () -> Unit) {
    Image(
        painter = painterResource(R.drawable.ic_outline_cancel_white_24),
        contentDescription = Strings.empty,
        modifier = Modifier
            .clickable { onClick.invoke() }
            .size(Dimens.cancelImageSize)
    )
}