package hu.ujszaszik.qrreader.reader

import android.os.Parcelable
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.parcelize.Parcelize

class ReaderViewModel @ViewModelInject constructor() : ViewModel() {

    private val _barcodeResult = MutableLiveData<BarcodeResult>()
    val barcodeResult: LiveData<BarcodeResult> get() = _barcodeResult

    fun onBarcodeDetected(rawValue: String) {
        _barcodeResult.postValue(BarcodeResult.Success(rawValue))
    }

    fun onBarcodeError(throwable: Throwable) {
        _barcodeResult.postValue(BarcodeResult.Error(throwable))
    }

    sealed class BarcodeResult: Parcelable {
        @Parcelize class Success(val rawValue: String) : BarcodeResult()
        @Parcelize class Error(val throwable: Throwable) : BarcodeResult()
    }
}