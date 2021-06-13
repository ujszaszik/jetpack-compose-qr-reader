package hu.ujszaszik.qrreader.extension

import android.os.Parcelable
import androidx.navigation.NavHostController

fun <T : Parcelable> NavHostController.getParcelable(key: String): T? {
    return previousBackStackEntry?.arguments?.getParcelable<T>(key)
}