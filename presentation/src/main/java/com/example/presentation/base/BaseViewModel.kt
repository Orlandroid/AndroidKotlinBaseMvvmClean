package com.example.presentation.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.di.CoroutineDispatchers
import com.example.domain.state.Result
import com.example.presentation.helpers.NetworkHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import java.net.SocketTimeoutException

abstract class BaseViewModel constructor(
    protected val coroutineDispatchers: CoroutineDispatchers, val networkHelper: NetworkHelper
) : ViewModel() {

    enum class ErrorType {
        NETWORK, TIMEOUT, UNKNOWN
    }

    suspend inline fun <T> safeApiCall(
        result: MutableStateFlow<Result<T>>,
        coroutineDispatchers: CoroutineDispatchers,
        crossinline apiToCall: suspend () -> Unit,
    ) {
        viewModelScope.launch(coroutineDispatchers.io) {
            result.emit(Result.Loading)
            try {
                if (!networkHelper.isNetworkConnected()) {
                    result.emit(Result.ErrorNetwork(""))
                    return@launch
                }
                apiToCall()
            } catch (e: Exception) {
                withContext(coroutineDispatchers.main) {
                    e.printStackTrace()
                    Log.e("ApiCalls", "Call error: ${e.localizedMessage} code:$e", e.cause)
                    when (e) {
                        is HttpException -> {
                            val errorBody = e.response()?.errorBody()
                            val errorCode = e.response()?.code()
                            result.emit(
                                Result.Error(
                                    error = e.message(),
                                    errorCode = errorCode ?: -1,
                                    errorBody = errorBody.toString()
                                )
                            )
                            Log.w("Call error :", "code:$errorCode")
                        }

                        is SocketTimeoutException -> result.emit(Result.Error(ErrorType.TIMEOUT.name))
                        is IOException -> result.emit(Result.Error(ErrorType.NETWORK.name))
                        else -> result.emit(Result.Error(ErrorType.UNKNOWN.name))
                    }
                }
            }
        }
    }


}



