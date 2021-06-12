package hu.ujszaszik.qrreader

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel @ViewModelInject constructor() : ViewModel() {

    private val _permissionAction = MutableLiveData<PermissionAction>()
    val permissionAction: LiveData<PermissionAction> get() = _permissionAction

    fun onPermissionAction(action: PermissionAction) {
        _permissionAction.postValue(action)
    }

    sealed class PermissionAction {
        object CheckIfAlreadyGranted : PermissionAction()
        object Granted : PermissionAction()
    }
}