package com.example.presentation.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.example.domain.state.Result

fun <T> Fragment.observeApiResult(
    liveData: LiveData<Result<T>>,
    isLoadingState: (Boolean) -> Unit = {},
    onLoading: () -> Unit = { },
    onFinishLoading: () -> Unit = { },
    haveTheViewProgress: Boolean = true,
    shouldCloseTheViewOnApiError: Boolean = false,
    onError: ((errorBody: String) -> Unit)? = null,
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
        isLoadingState(isLoading)
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
                    onError(apiState.errorBody)
                }
            }

            is Result.SocketTimeoutException -> {
                showErrorApi(shouldCloseTheViewOnApiError)
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
