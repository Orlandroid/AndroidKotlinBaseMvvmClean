package com.example.androidbase.presentation.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.androidbase.R
import com.example.androidbase.domain.state.Result
import com.example.androidbase.presentation.alerts.MainAlert
import com.example.androidbase.presentation.alerts.MainAlert.Companion.ERROR_MESSAGE
import com.example.androidbase.presentation.ui.MainActivity


fun Fragment.showProgress() {
    (requireActivity() as MainActivity).showProgress()
}

fun Fragment.hideProgress() {
    (requireActivity() as MainActivity).hideProgress()
}

fun Fragment.shouldShowProgress(isLoading: Boolean) {
    (requireActivity() as MainActivity).shouldShowProgress(isLoading)
}


fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}


fun Fragment.navigate(accion: NavDirections) {
    findNavController().navigate(accion)
}


fun Fragment.showErrorApi(shouldCloseTheViewOnApiError: Boolean = false) {
    val dialog =
        MainAlert(
            kindOfMessage = ERROR_MESSAGE,
            messageBody = getString(R.string.error_service),
            clickOnAccept = {
                if (shouldCloseTheViewOnApiError) {
                    findNavController().popBackStack()
                }
            }
        )
    activity?.let { dialog.show(it.supportFragmentManager, "alertMessage") }
}

fun Fragment.showErrorNetwork(shouldCloseTheViewOnApiError: Boolean = false) {
    val dialog = MainAlert(
        kindOfMessage = ERROR_MESSAGE,
        messageBody = getString(R.string.verifica_conexion),
        clickOnAccept = {
            if (shouldCloseTheViewOnApiError) {
                findNavController().popBackStack()
            }
        }
    )
    activity?.let { dialog.show(it.supportFragmentManager, "alertMessage") }
}


fun <T> Fragment.observeApiResult(
    liveData: LiveData<Result<T>>,
    onLoading: () -> Unit = { },
    onFinishLoading: () -> Unit = { },
    haveTheViewProgress: Boolean = true,
    shouldCloseTheViewOnApiError: Boolean = false,
    onError: (() -> Unit)? = null,
    noData: () -> Unit = {},
    onSuccess: (data: T) -> Unit,
) {
    liveData.observe(viewLifecycleOwner) { apiState ->
        fun handleStatusOnLoading(isLoading: Boolean) {
            if (isLoading) {
                onLoading()
            } else {
                onFinishLoading()
            }
        }

        val isLoading = apiState is Result.Loading
        if (haveTheViewProgress) {
            shouldShowProgress(isLoading)
        } else {
            handleStatusOnLoading(isLoading)
        }
        when (apiState) {
            is Result.Success -> {
                if (apiState.data != null) {
                    onSuccess(apiState.data)
                }
            }
            is Result.Error -> {
                if (onError == null) {
                    showErrorApi(shouldCloseTheViewOnApiError)
                } else {
                    onError()
                }
            }
            is Result.ErrorNetwork -> {
                showErrorNetwork(shouldCloseTheViewOnApiError)
            }
            is Result.EmptyList -> {
                noData()
            }
            else -> {}
        }
    }
}
