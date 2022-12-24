package com.example.androidbase.presentation.ui.users

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidbase.data.Repository
import com.example.androidbase.data.di.CoroutineDispatchers
import com.example.androidbase.domain.entities.remote.UsersResponse
import com.example.androidbase.presentation.base.BaseViewModel
import com.example.androidbase.presentation.helpers.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.androidbase.domain.state.Result
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repository: Repository,
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper
) : BaseViewModel(coroutineDispatchers, networkHelper) {

    private val errorNetwork = "Error verifica tu conexion"

    private val _userResponse = MutableLiveData<Result<UsersResponse>>()
    val userResponse: LiveData<Result<UsersResponse>>
        get() = _userResponse

    fun getUsers() {
        viewModelScope.launch {
            safeApiCall(_userResponse, coroutineDispatchers) {
                val response = repository.getUser()
                withContext(Dispatchers.Main) {
                    _userResponse.value = Result.Success(response)
                    Log.w("ANDROID",response.data.toString())
                }
            }
        }
    }

}