package hu.ujszaszik.qrreader.reader.draw

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path

class Curve private constructor() {

    internal var left = 0F
    internal var right = 0F
    internal var top = 0F
    internal var bottom = 0F
    internal var startAngle = 0F
    internal var sweepAngle = 0F

    companion object {
        fun create(): Curve = Curve()
    }

    fun left(left: Float): Curve = apply { this.left = left }

    fun right(right: Float): Curve = apply { this.right = right }

    fun top(top: Float): Curve = apply { this.top = top }

    fun bottom(bottom: Float): Curve = apply { this.bottom = bottom }

    fun startAngle(startAngle: Float): Curve = apply { this.startAngle = startAngle }

    fun sweepAngle(sweepAngle: Float): Curve = apply { this.sweepAngle = sweepAngle }
}

fun Path.curveTo(curve: Curve) {
    val rect = Rect(curve.left, curve.top, curve.right, curve.bottom)
    arcTo(rect, curve.startAngle, curve.sweepAngle, true)
}