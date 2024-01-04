package com.example.presentation.extensions

import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.domain.state.Result
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

fun <T> Fragment.observeFlow(
    progressBar: ProgressBar? = null,
    stateFlow: StateFlow<Result<T>>,
    shouldCloseTheViewOnApiError: Boolean = false,
    onError: ((errorBody: String) -> Unit)? = null,
    onSuccess: (data: T) -> Unit
) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            stateFlow.collect {
                if (it is Result.Loading) {
                    progressBar?.visible()
                } else {
                    progressBar?.gone()
                }
                when (it) {
                    Result.EmptyList -> {}
                    is Result.Error -> {
                        if (onError == null) {
                            showErrorApi(shouldCloseTheViewOnApiError)
                        } else {
                            onError(it.errorBody)
                        }
                    }

                    is Result.ErrorNetwork -> {
                        showErrorNetwork(shouldCloseTheViewOnApiError)
                    }

                    Result.Loading -> {

                    }

                    is Result.SocketTimeoutException -> {
                        showErrorApi(shouldCloseTheViewOnApiError)
                    }

                    is Result.Success -> {
                        if (it.data != null) {
                            onSuccess(it.data)
                        }
                    }
                }
            }
        }
    }
}