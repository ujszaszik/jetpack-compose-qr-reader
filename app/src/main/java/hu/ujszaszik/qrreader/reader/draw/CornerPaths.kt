package hu.ujszaszik.qrreader.reader.draw

import androidx.compose.ui.graphics.Path
import hu.ujszaszik.qrreader.extension.half

class CornerPaths private constructor() {
    private var left = 0F
    private var right = 0F
    private var top = 0F
    private var bottom = 0F
    private var cornerRadius = 0F
    private var cornerLength = 0F

    private val path = Path()

    companion object {
        fun create(): CornerPaths = CornerPaths()
    }

    fun left(left: Float): CornerPaths = apply { this.left = left }

    fun right(right: Float): CornerPaths = apply { this.right = right }

    fun top(top: Float): CornerPaths = apply { this.top = top }

    fun bottom(bottom: Float): CornerPaths = apply { this.bottom = bottom }

    fun cornerRadius(radius: Float): CornerPaths = apply { this.cornerRadius = radius }

    fun cornerLength(length: Float): CornerPaths = apply { this.cornerLength = length }

    fun drawnPath(): Path {
        return path.also {
            drawTopLeft()
            drawTopRight()
            drawBottomLeft()
            drawBottomRight()
        }
    }

    private fun drawTopLeft() {
        path.moveTo(left, (top + cornerRadius))
        path.curveTo(
            Curve.create()
                .left(left)
                .top(top)
                .right(left + cornerRadius)
                .bottom(top + cornerRadius)
                .startAngle(180f)
                .sweepAngle(90f)
        )

        path.moveTo(left + (cornerRadius.half()), top)
        path.lineTo(left + (cornerRadius.half()) + cornerLength, top)

        path.moveTo(left, top + (cornerRadius.half()))
        path.lineTo(left, top + (cornerRadius.half()) + cornerLength)
    }

    private fun drawTopRight() {
        path.moveTo(right - cornerRadius, top)

        path.curveTo(
            Curve.create()
                .left(right - cornerRadius)
                .top(top)
                .right(right)
                .bottom(top + cornerRadius)
                .startAngle(270f)
                .sweepAngle(90f)
        )

        path.moveTo(right - (cornerRadius.half()), top)
        path.lineTo(right - (cornerRadius.half()) - cornerLength, top)

        path.moveTo(right, top + (cornerRadius.half()))
        path.lineTo(right, top + (cornerRadius.half()) + cornerLength)
    }

    private fun drawBottomLeft() {
        path.moveTo(left, bottom - cornerRadius)

        path.curveTo(
            Curve.create()
                .left(left)
                .top(bottom - cornerRadius)
                .right(left + cornerRadius)
                .bottom(bottom)
                .startAngle(90f)
                .sweepAngle(90f)
        )

        path.moveTo(left + (cornerRadius.half()), bottom)
        path.lineTo(left + (cornerRadius.half()) + cornerLength, bottom)

        path.moveTo(left, bottom - (cornerRadius.half()))
        path.lineTo(left, bottom - (cornerRadius.half()) - cornerLength)
    }

    private fun drawBottomRight() {
        path.moveTo(left, bottom - cornerRadius)
        path.curveTo(
            Curve.create()
                .left(right - cornerRadius)
                .top(bottom - cornerRadius)
                .right(right)
                .bottom(bottom)
                .startAngle(0f)
                .sweepAngle(90f)
        )

        path.moveTo(right - (cornerRadius.half()), bottom)
        path.lineTo(right - (cornerRadius.half()) - cornerLength, bottom)

        path.moveTo(right, bottom - (cornerRadius.half()))
        path.lineTo(right, bottom - (cornerRadius.half()) - cornerLength)
    }
}