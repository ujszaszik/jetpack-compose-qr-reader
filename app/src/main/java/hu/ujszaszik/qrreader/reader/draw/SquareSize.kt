package hu.ujszaszik.qrreader.reader.draw

class SquareSize(
    private val ratio: Float,
    private val size: Float
) {

    val height: Float
        get() = size * ratio / 100

    val width: Float
        get() = height
}
