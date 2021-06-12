package hu.ujszaszik.qrreader.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

@Composable
fun GradientButton(
    text: String,
    textColor: Color,
    textSize: TextUnit,
    backgroundColor: Color,
    gradientStartColor: Color,
    gradientEndColor: Color,
    height: Dp,
    borderWidth: Dp,
    cornerRadius: Dp,
    onClick: () -> Unit
) {
    val gradient = Brush.horizontalGradient(listOf(gradientStartColor, gradientEndColor))
    Button(
        onClick = { onClick.invoke() },
        colors = ButtonDefaults.buttonColors(backgroundColor),
        border = BorderStroke(borderWidth, gradient),
        shape = RoundedCornerShape(cornerRadius),
        modifier = Modifier.height(height)
    ) {
        Text(text = text, color = textColor, fontSize = textSize)
    }
}