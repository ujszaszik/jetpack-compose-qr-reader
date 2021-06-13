package hu.ujszaszik.qrreader.analyzer

class BarcodeListener(private val block: (String) -> Unit) {

    fun onDetected(rawValue: String) {
        block.invoke(rawValue)
    }
}

class BarcodeErrorListener(private val block: (Throwable) -> Unit) {

    fun onError(throwable: Throwable) {
        block.invoke(throwable)
    }
}