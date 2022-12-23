package com.example.androidbase.presentation.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.androidbase.R
import com.example.androidbase.domain.state.ApiState
import com.example.androidbase.presentation.ui.MainActivity
import com.example.androidbase.domain.state.Result
import com.example.androidbase.presentation.alerts.MainAlert
import com.example.androidbase.presentation.alerts.MainAlert.Companion.ERROR_MESSAGE


fun Fragment.showProgress() {
    (requireActivity() as MainActivity).showProgress()
}

fun Fragment.hideProgress() {
    (requireActivity() as MainActivity).hideProgress()
}


fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}


fun Fragment.navigate(accion: NavDirections) {
    findNavController().navigate(accion)
}


fun Fragment.showErrorApi(wantExitFromView: Boolean = false) {
    val dialog = MainAlert(ERROR_MESSAGE, getString(R.string.error_service))
    activity?.let { dialog.show(it.supportFragmentManager, "alertMessage") }
    if (wantExitFromView) {
        findNavController().popBackStack()
    }
}

fun Fragment.showErrorNetwork() {
    val dialog = MainAlert(ERROR_MESSAGE, getString(R.string.verifica_conexion))
    activity?.let { dialog.show(it.supportFragmentManager, "alertMessage") }
}


fun <T> Fragment.observeApiResultGeneric(
    liveData: LiveData<ApiState<T>>,
    onLoading: () -> Unit = { },
    onFinishLoading: () -> Unit = { },
    haveTheViewProgress: Boolean = true,
    shouldCloseTheViewOnApiError: Boolean = false,
    onError: (() -> Unit)? = null,
    noData: () -> Unit = {},
    onSuccess: (data: T) -> Unit,
) {
    liveData.observe(viewLifecycleOwner) { apiState ->
        if (apiState is ApiState.Loading) {
            if (haveTheViewProgress) {
                showProgress()
            } else {
                onLoading()
            }
        } else {
            if (haveTheViewProgress) {
                hideProgress()
            } else {
                onFinishLoading()
            }
        }
        when (apiState) {
            is ApiState.Success -> {
                if (apiState.data != null) {
                    onSuccess(apiState.data)
                }
            }
            is ApiState.Error -> {
                if (onError == null) {
                    //showErrorApi(shouldCloseTheViewOnApiError)
                } else {
                    onError()
                }
            }
            is ApiState.ErrorNetwork -> {
                //showErrorNetwork(shouldCloseTheViewOnApiError)
            }
            is ApiState.NoData -> {
                noData()
            }
            else -> {}
        }
    }
}


fun <T> Fragment.observeApiResult(
    apiResult: LiveData<Result<T>>,
    onLoading: () -> Unit = {},
    onSuccess: () -> Unit = {},
    emptyList: () -> Unit = {},
    error: () -> Unit = {},
    errorNetwork: () -> Unit = {}
) {
    apiResult.observe(viewLifecycleOwner) {
        if (it is Result.Loading) {
            onLoading()
            (requireActivity() as MainActivity).showProgress()
        } else {
            (requireActivity() as MainActivity).hideProgress()
        }
        when (it) {
            is Result.Success -> {
                onSuccess()
            }
            is Result.EmptyList -> {
                emptyList()
            }
            is Result.Error -> {
                error()
                showErrorApi()
            }
            is Result.ErrorNetwork -> {
                errorNetwork()
                showErrorNetwork()
            }
            else -> {}
        }
    }
}