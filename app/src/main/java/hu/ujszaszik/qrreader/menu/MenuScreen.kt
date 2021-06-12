package hu.ujszaszik.qrreader.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import hu.ujszaszik.qrreader.MainViewModel
import hu.ujszaszik.qrreader.MainViewModel.PermissionAction.CheckIfAlreadyGranted
import hu.ujszaszik.qrreader.R
import hu.ujszaszik.qrreader.resources.Dimens
import hu.ujszaszik.qrreader.resources.Strings
import hu.ujszaszik.qrreader.view.GradientButton

@Composable
fun MenuScreen(viewModel: MainViewModel) {
    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(R.drawable.qr_logo), contentDescription = Strings.empty)
            Spacer(modifier = Modifier.height(Dimens.marginExtra))
            GradientButton(
                text = Strings.labelOpenCamera,
                textColor = Dimens.buttonTextColor,
                textSize = Dimens.defaultTextSize,
                backgroundColor = Color.White,
                gradientStartColor = Dimens.gradientStartColor,
                gradientEndColor = Dimens.gradientEndColor,
                height = Dimens.defaultButtonHeight,
                borderWidth = Dimens.defaultButtonStrokeWidth,
                cornerRadius = Dimens.defaultButtonCornerRadius,
                onClick = { viewModel.onPermissionAction(CheckIfAlreadyGranted) })
        }
    }
}