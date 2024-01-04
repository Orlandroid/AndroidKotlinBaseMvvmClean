package com.example.presentation.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.domain.state.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

fun <T> Fragment.observeApiResultFlow(
    liveData: StateFlow<Result<T>>,
    isLoadingState: (Boolean) -> Unit = {},
    onLoading: () -> Unit = { },
    onFinishLoading: () -> Unit = { },
    haveTheViewProgress: Boolean = true,
    shouldCloseTheViewOnApiError: Boolean = false,
    onError: ((errorBody: String) -> Unit)? = null,
    noData: () -> Unit = {},
    onSuccess: (data: T) -> Unit,
) {
    lifecycleScope.launch {
        delay(1.seconds)
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            liveData.collect { apiState ->
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
                    Result.EmptyList -> {
                        noData()
                    }

                    is Result.Error -> {
                        if (onError == null) {
                            showErrorApi(shouldCloseTheViewOnApiError)
                        } else {
                            onError(apiState.errorBody)
                        }
                    }

                    is Result.ErrorNetwork -> {
                        showErrorNetwork(shouldCloseTheViewOnApiError)
                    }

                    is Result.SocketTimeoutException -> {
                        showErrorApi(shouldCloseTheViewOnApiError)
                    }

                    is Result.Success -> {
                        if (apiState.data != null) {
                            onSuccess(apiState.data)
                        }
                    }

                    is Result.Loading -> {

                    }
                }
            }
        }
    }
}
