package com.example.androidbase.presentation.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidbase.data.di.CoroutineDispatchers
import com.example.androidbase.presentation.helpers.NetworkHelper
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import java.net.SocketTimeoutException
import com.example.androidbase.domain.state.Result

abstract class BaseViewModel constructor(
    protected val coroutineDispatchers: CoroutineDispatchers,
    protected val networkHelper: NetworkHelper
) : ViewModel() {

    enum class ErrorType {
        NETWORK,
        TIMEOUT,
        UNKNOWN
    }

    suspend inline fun <T> safeApiCall(
        result: MutableLiveData<Result<T>>,
        coroutineDispatchers: CoroutineDispatchers,
        crossinline apiToCall: suspend () -> Unit,
    ) {
        viewModelScope.launch(coroutineDispatchers.io) {
            try {
                withContext(coroutineDispatchers.main) {
                    result.value = Result.Loading
                }
                apiToCall()
            } catch (e: Exception) {
                withContext(coroutineDispatchers.main) {
                    e.printStackTrace()
                    Log.e("ApiCalls", "Call error: ${e.localizedMessage}", e.cause)
                    when (e) {
                        is HttpException -> {
                            val errorBody = e.response()?.errorBody()
                            val errorCode = e.response()?.code()
                            result.value = Result.Error(
                                error = e.message(),
                                errorCode = errorCode ?: -1,
                                errorBody = errorBody.toString()
                            )
                        }
                        is SocketTimeoutException -> result.value =
                            Result.Error(ErrorType.TIMEOUT.name)
                        is IOException -> result.value = Result.Error(ErrorType.NETWORK.name)
                        else -> result.value = Result.Error(ErrorType.UNKNOWN.name)
                    }
                }
            }
        }
    }


}



