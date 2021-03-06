package hu.ujszaszik.qrreader.reader.ui

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import hu.ujszaszik.qrreader.extension.OPAQUE
import hu.ujszaszik.qrreader.extension.half
import hu.ujszaszik.qrreader.reader.draw.CornerPaths
import hu.ujszaszik.qrreader.reader.draw.SquareSize

@Composable
fun QrTargetSquare(
    color: Color,
    sizePercentPortrait: Float,
    sizePercentLandscape: Float,
    strokeWidth: Float,
    cornerRadius: Float,
    cornerLength: Float,
    alpha: Float = OPAQUE,
) {
    val orientation = LocalConfiguration.current.orientation
    val isPortrait = orientation == Configuration.ORIENTATION_PORTRAIT

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer(alpha = alpha)
    ) {
        val sizePercent = if (isPortrait) sizePercentPortrait else sizePercentLandscape
        val squareSize = SquareSize(size.width, sizePercent)

        drawPath(
            CornerPaths.create()
                .left((size.width - squareSize.width).half())
                .top((size.height - squareSize.width).half())
                .right((size.width - squareSize.width).half() + squareSize.height)
                .bottom((size.height - squareSize.width).half() + squareSize.width)
                .cornerRadius(cornerRadius)
                .cornerLength(cornerLength)
                .drawnPath(),
            color = color,
            style = Stroke(width = strokeWidth)
        )
    }
}